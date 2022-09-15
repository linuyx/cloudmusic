package com.hanfz.pojo.param.musictype;

import com.hanfz.valid.Insert;
import com.hanfz.valid.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author Linuyx
 * @Description 音乐类型编辑请求体
 * @Date Created in 2021-12-22 15:55
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EditParam {

    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;

    @NotBlank(message = "音乐类型名称不能为空", groups = {Insert.class, Update.class})
    @Size(max = 32, message = "音乐类型名称长度不能超过32个字符", groups = {Insert.class, Update.class})
    private String name;

}
