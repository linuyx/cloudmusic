package com.hanfz.pojo.param.singer;

import com.hanfz.valid.Insert;
import com.hanfz.valid.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

/**
 * @Author Linuyx
 * @Description 歌手编辑请求体
 * @Date Created in 2021-12-22 18:19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EditParam {

    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;

    @NotBlank(message = "歌手姓名不能为空", groups = {Insert.class, Update.class})
    @Size(max = 32, message = "歌手姓名不能超过32个字符", groups = {Insert.class, Update.class})
    private String name;

    @NotNull(message = "性别不能为空", groups = {Insert.class, Update.class})
    @Min(value = 0, message = "性别只能为1(男)或0(女)", groups = {Insert.class, Update.class})
    @Max(value = 1, message = "性别只能为1(男)或0(女)", groups = {Insert.class, Update.class})
    private Integer sex;

    @NotBlank(message = "歌手简介不能为空", groups = {Insert.class, Update.class})
    @Size(max = 255, message = "歌手简介不能超过25个字符", groups = {Insert.class, Update.class})
    private String introduction;

}
