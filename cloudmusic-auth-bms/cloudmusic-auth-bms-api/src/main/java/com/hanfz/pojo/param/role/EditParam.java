package com.hanfz.pojo.param.role;

import com.hanfz.valid.Insert;
import com.hanfz.valid.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-29 14:57
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditParam implements Serializable {

    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;

    @NotBlank(message = "角色名称不能为空", groups = {Insert.class, Update.class})
    @Size(max = 32, message = "角色名称长度不能超过32个字符", groups = {Insert.class, Update.class})
    private String name;

    /**
     * 资源id集合
     */
    private List<Long> resourceIds;

}
