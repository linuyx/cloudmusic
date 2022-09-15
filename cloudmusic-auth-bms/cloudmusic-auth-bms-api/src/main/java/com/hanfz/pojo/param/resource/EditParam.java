package com.hanfz.pojo.param.resource;

import com.hanfz.valid.Insert;
import com.hanfz.valid.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-24 21:19
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditParam implements Serializable {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;

    @NotBlank(message = "url(资源路径)不能为空", groups = {Insert.class, Update.class})
    @Size(max = 255, message = "url(资源路径)长度不能超过255个字符", groups = {Insert.class, Update.class})
    private String url;

    @NotBlank(message = "method(请求方法)不能为空", groups = {Insert.class, Update.class})
    @Pattern(regexp = "(GET|POST|PUT|DELETE)", message = "method(请求方法)只能为:GET|POST|PUT|DELETE",
            groups = {Insert.class, Update.class} )
    private String method;

    @NotBlank(message = "name(资源名称)不能为空", groups = {Insert.class, Update.class})
    @Size(max = 64, message = "name(资源名称)长度不能超过64个字符", groups = {Insert.class, Update.class})
    private String name;

}
