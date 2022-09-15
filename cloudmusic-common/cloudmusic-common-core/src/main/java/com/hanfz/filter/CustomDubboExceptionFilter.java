/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hanfz.filter;

import com.alibaba.druid.sql.visitor.functions.Bin;
import com.hanfz.enums.ResponseEnum;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.response.ResponseData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

import javax.validation.ValidationException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * 自定义Dubbo异常拦截器-如果是能够被全局异常处理的异常直接返回(GlobalExceptionHandler)
 * ExceptionInvokerFilter
 * <p>
 * Functions:
 * <ol>
 * <li>unexpected exception will be logged in ERROR level on provider side. Unexpected exception are unchecked
 * exception not declared on the interface</li>
 * <li>Wrap the exception not introduced in API package into RuntimeException. Framework will serialize the outer exception but stringnize its cause in order to avoid of possible serialization problem on client side</li>
 * </ol>
 */
@Slf4j
@Activate(group = CommonConstants.PROVIDER)
public class CustomDubboExceptionFilter implements Filter, Filter.Listener {
    private Logger logger = LoggerFactory.getLogger(CustomDubboExceptionFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            try {
                Throwable exception = appResponse.getException();

                //自定义业务异常
                if(exception instanceof GlobalException ex) {
                    log.warn("[业务异常] {code: {}, message: {}}", ex.getCode(), ex.getMessage());
                    appResponse.setException(null);
                    appResponse.setValue(ResponseData.fail(ex.getCode(), ex.getMessage()));
                    return;
                }

                //参数校验异常
                if(exception instanceof ValidationException ex) {
                    log.warn("[参数校验异常] {}", ex.toString());
                    appResponse.setException(null);
                    appResponse.setValue(ResponseData.fail(ResponseEnum.PARAM_ERROR.getCode(), ex.getMessage()));
                    return;
                }

                //token签名异常
                if(exception instanceof SignatureException ex) {
                    log.warn("[Jwt签名异常] {}", ex.toString());
                    appResponse.setException(null);
                    appResponse.setValue(ResponseData.fail(ResponseEnum.TOKEN_SIGNATURE_ERROR.getCode(),
                            ResponseEnum.TOKEN_SIGNATURE_ERROR.getMessage()));
                    return;
                }

                //token过期异常
                if(exception instanceof ExpiredJwtException ex) {
                    log.warn("[Jwt过期异常] {}", ex.toString());
                    appResponse.setException(null);
                    appResponse.setValue(ResponseData.fail(ResponseEnum.TOKEN_EXPIRED_ERROR.getCode(),
                            ResponseEnum.TOKEN_EXPIRED_ERROR.getMessage()));
                    return;
                }

                //其他异常
                if(exception instanceof Exception ex) {
                    log.error( "[系统异常] {}", ex.toString());
                    appResponse.setException(null);
                    appResponse.setValue(ResponseData.fail(ResponseEnum.SYSTEM_ERROR.getCode(),
                            ResponseEnum.SYSTEM_ERROR.getMessage()));
                }
            } catch (Throwable e) {
                logger.warn("Fail to ExceptionFilter when called by " + RpcContext.getServiceContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
        logger.error("Got unchecked and undeclared exception which called by " + RpcContext.getServiceContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
    }

    // For test purpose
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}

