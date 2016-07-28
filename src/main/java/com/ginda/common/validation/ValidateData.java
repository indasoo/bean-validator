package com.ginda.common.validation;

import java.lang.annotation.*;

/**
 * 数据值域
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateData {

    /* 字段名称 */
    String name() default "";

    /* 是否必须 */
    boolean required() default false;

    /* 常量值 */
    String constantValue() default "";

    /* 最小长度 */
    int minLength() default 0;

    /* 最大长度 */
    int maxLength() default Integer.MAX_VALUE;

    /* 通用正则类型 */
    RegexType regexType() default RegexType.NONE;

    /* 自定义正则验证 */
    String regexExpression() default "";

    /* 值域 */
    Class<? extends Enum<?>> valueRangeEnumClazz() default EnumClazz.class;

    /* 值域数据获取方法 */
    String valueRangeEnumMethod() default "name";

    /**
     * 缺省值范围
     */
    enum EnumClazz {
    }
}
