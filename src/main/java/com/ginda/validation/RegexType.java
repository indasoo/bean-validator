package com.ginda.validation;

/**
 * 常用的数据类型枚举
 */
public enum RegexType {

    NONE(null, null),

    /* 格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商 */
    EMAIL("邮件地址", "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?"),

    /* 居民身份证号码18位，第一位不能为0，最后一位可能是数字或字母，中间16位为数字 \d同[0-9] */
    IDCARD("身份证号码", "[1-9]\\d{16}[a-zA-Z0-9]{1}"),

    /* 一位或多位0-9之间的整数 */
    DIGTI("整数（正整数和负整数）", "\\-?[1-9]\\d+"),

    /* 通用日期格式，支持yyyy-MM-dd、yyyy:MM:dd、yyyy/MM/dd */
    DATE("日期", "^([\\d]{4})([-:/])((0?[1-9])|((1)([0-2])))([-:/])((0?[1-9])|((1)[0-9])|((2)[0-9])|((3)[0-1]))$"),

    /* 日期yyyy-MM-dd格式*/
    DATE_HYPHEN("日期(yyyy-MM-dd)", "^([\\d]{4})([-])((0?[1-9])|((1)([0-2])))([-])((0?[1-9])|((1)[0-9])|((2)[0-9])|((3)[0-1]))$"),

    /*
     * 手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     * 移动、联通、电信运营商的号码段
     * <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     * 150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     * <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     * <p>电信的号段：133、153、180（未启用）、189</p>
     */
    MOBILE("手机号码", "(\\+\\d+)?1[3458]\\d{9}$"),
    ;

    /* 描述 */
    private String description;

    /* 描述 */
    private String regex;

    RegexType(String description, String regex) {
        this.description = description;
        this.regex = regex;
    }

    public String description() {
        return description;
    }

    public String regex() {
        return regex;
    }
}