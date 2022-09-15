package com.hanfz.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hanfz.AuthDubboService;
import com.hanfz.enums.ResponseEnum;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.auth.AuthParam;
import com.hanfz.pojo.request.RequestDataBms;
import com.hanfz.pojo.response.ResponseData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Author Linuyx
 * @Description GateWay过滤器
 * @Date Created in 2021-09-13 21:30
 */

@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @DubboReference  private AuthDubboService authDubboService;

    /**
     * bms端放行url-后台登录
     */
    private final String BMS_RELEASE_URL = "/auth-bms/login";

    /**
     * 存储token的key
     */
    private final String TOKEN = "token";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取url
        String requestPath = exchange.getRequest().getURI().getPath();

        String urlPrefix = requestPath.substring(0, requestPath.indexOf("/", 1));
        if ("/app".equals(urlPrefix)) {
            return appAuth(exchange, chain);
        } else if ("/bms".equals(urlPrefix)) {
            return bmsAuth(exchange, chain);
        } else {
            return structureResult(exchange, ResponseEnum.CUSTOM_ERROR.getCode(), "URL不被系统允许");
        }
    }

    /**
     * app端鉴权
     *
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> appAuth(ServerWebExchange exchange, GatewayFilterChain chain) {
        //app端目前直接放行
        return chain.filter((exchange));
    }

    /**
     * bms端鉴权
     *
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> bmsAuth(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取request
        ServerHttpRequest request = exchange.getRequest();
        //获取path
        String path = request.getURI().getPath();
        //获取访问下游服务的url
        String url = path.substring(path.indexOf("/", 1));

        //判断是否是放行地址
        if (BMS_RELEASE_URL.equals(url)) {
            return chain.filter(exchange);
        }

        //从请求头中获取token
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(TOKEN);
        //判断token是否为空
        if (token == null || "".equals(token)) {
            return structureResult(exchange, ResponseEnum.TOKEN_NOTNULL_ERROR.getCode(),
                    ResponseEnum.TOKEN_NOTNULL_ERROR.getMessage());
        }

        //请求方式
        String method = request.getMethodValue();

        //调用接口判断是否有权限
        ResponseData<RequestDataBms> response = authDubboService.auth(new AuthParam(token, url, method));

        //如果有权限就放行
        if (ResponseEnum.SUCCESS.getCode().equals(response.getCode())) {
            //将requestData添加到header中
            request = exchange
                    .getRequest()
                    .mutate()
                    .header("requestData", JSON.toJSONString(response.getData()))
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        }

        //无权限访问或其他错误,返回信息
        return structureResult(exchange, response.getCode(), response.getMessage());
    }

    /**
     * 构造返回结果
     *
     * @param exchange
     * @param code     响应状态码
     * @param message  响应信息
     * @return
     */
    public Mono<Void> structureResult(ServerWebExchange exchange, Integer code, String message) {
        //获取response响应
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        //创建响应结果,并序列化为JSON
        ResponseData<Object> result = ResponseData.fail(code, message);
        String resultJson = JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
        //将响应结果输入到流中
        DataBuffer data = response.bufferFactory().wrap(resultJson.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(data));
    }

    /**
     * 指定GlobalFilter的执行顺序
     * <p>
     * Order越小,越先执行
     * 默认过滤器DefaultFilter和路由过滤器Filters,order值由Spring指定,默认从1开始
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }

}
