package com.ginda.common.validation;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 数据检验工具
 */
public class Validator {

    /**
     * 数据验证
     *
     * @param Obj 数据
     * @throws ValidatorException 数据检验异常
     */
    public static void validate(Object Obj) throws ValidatorException {

        Field[] fields = Obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 判断字段是否被标记
            if (field.isAnnotationPresent(ValidateData.class)) {
                // 设置允许私有方法访问
                field.setAccessible(true);

                ValidateData validateData = field.getAnnotation(ValidateData.class);

                Object value = null;
                try {
                    value = field.get(Obj);
                } catch (IllegalAccessException e) {
                    new ValidatorException(e.getMessage());
                }

                // 值为空时，进行必要性检查，否则进行数据验证
                if (value == null) {
                    // 是否必要检查
                    if (validateData.required()) {
                        throw new ValidatorException(validateData.name() + "不能为空");
                    }
                } else {
                    String strValue = String.valueOf(value);

                    // 常量值检查
                    if (StringUtils.isNotEmpty(validateData.constantValue())
                            && !StringUtils.equals(validateData.constantValue(), strValue)) {
                        throw new ValidatorException(validateData.name() + "与约定值不一致");
                    }

                    // 长度检查
                    if (validateData.minLength() != 0 && strValue.length() < validateData.minLength()) {
                        throw new ValidatorException(validateData.name() + "长度不能小于" + validateData.minLength());
                    }

                    if (validateData.maxLength() != Integer.MAX_VALUE && strValue.length() > validateData.maxLength()) {
                        throw new ValidatorException(validateData.name() + "长度不能超过" + validateData.maxLength());
                    }

                    // 数据类型验证
                    if (validateData.regexType() != RegexType.NONE) {
                        if (!Pattern.matches(validateData.regexType().regex(), strValue)) {
                            throw new ValidatorException(validateData.name() + "格式不正确");
                        }
                    }

                    // 自定义正则表达式验证
                    if (StringUtils.isNotEmpty(validateData.regexExpression()) && !Pattern.matches(validateData.regexExpression(), strValue)) {
                        throw new ValidatorException(validateData.name() + "格式不正确");
                    }

                    // 值域验证
                    if (validateData.valueRangeEnumClazz() != ValidateData.EnumClazz.class) {
                        boolean isExist = false;
                        Method method = null;
                        try {
                            method = validateData.valueRangeEnumClazz().getMethod(validateData.valueRangeEnumMethod());
                        } catch (NoSuchMethodException e) {
                            new ValidatorException(e.getMessage());
                        }
                        for (Enum item : (validateData.valueRangeEnumClazz().getEnumConstants())) {
                            Object obj = null;
                            try {
                                obj = method.invoke(item);
                            } catch (IllegalAccessException e) {
                                new ValidatorException(e.getMessage());
                            } catch (InvocationTargetException e) {
                                new ValidatorException(e.getMessage());
                            }

                            if (obj.equals(value)) {
                                isExist = true;
                            }
                        }

                        if (!isExist) {
                            throw new ValidatorException(validateData.name() + "不合法");
                        }
                    }
                }
            }
        }
    }
}
