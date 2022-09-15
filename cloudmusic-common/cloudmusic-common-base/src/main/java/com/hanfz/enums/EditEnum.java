package com.hanfz.enums;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-24 12:39
 */
public enum EditEnum {

    /**
     * 编辑枚举
     */
    INSERT("INSERT", "添加"),
    UPDATE("UPDATE", "更新");

    /**
     * 编辑类型
     */
    private String name;

    /**
     * 描述
     */
    private String message;

    EditEnum(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

}
