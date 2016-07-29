package com.ginda.validation;

/**
 * Created by Ginda.Tseng on 2016/7/26.
 */
public enum UserNameEnum {

    //用户角色类型
    ROLE_ADMIN("admin", "admin"),
    ROLE_PRODUCT("test", "test"),
    ;

    /** 编码 */
    private String code;

    /** 描述 */
    private String description;

    UserNameEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    public String code() {
        return code;
    }

    public String description() {
        return description;
    }
}
