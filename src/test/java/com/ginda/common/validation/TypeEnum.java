package com.ginda.common.validation;

/**
 * Created by Ginda.Tseng on 2016/7/26.
 */
public enum TypeEnum {

    //用户角色类型
    AGE_1(1, "admin"),
    AGE_2(2, "test"),
    ;

    /** 编码 */
    private int code;

    /** 描述 */
    private String description;

    TypeEnum(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int code() {
        return code;
    }

    public String description() {
        return description;
    }
}
