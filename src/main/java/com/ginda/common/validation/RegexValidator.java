package com.ginda.common.validation;

/**
 * 常用的数据类型验证工具
 */
public class RegexValidator {

    /* 数据类型 */
    private RegexType regexType = RegexType.NONE;

    /**
     * 构造函数
     * @param regexType 数据类型
     */
    public RegexValidator(RegexType regexType) {
        this.regexType = regexType;
    }

    /**
     * 验证服务
     * @param data 数据
     */
    public void validate(Object data) {

    }
}
