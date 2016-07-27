package com.ginda.common.validation;

import com.ginda.common.validation.annotation.DataValue;

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
            if (field.isAnnotationPresent(DataValue.class)) {
                // 设置允许私有方法访问
                field.setAccessible(true);

                DataValue dateValue = field.getAnnotation(DataValue.class);

                Object value = null;
                try {
                    value = field.get(Obj);
                } catch (IllegalAccessException e) {
                    new ValidatorException(e.getMessage());
                }

                // 值为空时，进行必要性检查，否则进行数据验证
                if (value == null) {
                    // 是否必要检查
                    if (dateValue.required()) {
                        throw new ValidatorException(dateValue.name() + "不能为空");
                    }
                } else {
                    String strValue = String.valueOf(value);
                    // 长度检查
                    if (dateValue.minLength() != 0 && strValue.length() < dateValue.minLength()) {
                        throw new ValidatorException(dateValue.name() + "长度不能小于" + dateValue.minLength());
                    }

                    if (dateValue.maxLength() != Integer.MAX_VALUE && strValue.length() > dateValue.maxLength()) {
                        throw new ValidatorException(dateValue.name() + "长度不能超过" + dateValue.maxLength());
                    }

                    // 数据类型验证
                    if (dateValue.regexType() != RegexType.NONE) {
                        if (!Pattern.matches(dateValue.regexType().regex(), strValue)) {
                            throw new ValidatorException(dateValue.name() + "格式不正确");
                        }
                    }

                    // 自定义正则表达式验证
                    if (!"".equals(dateValue.regexExpression()) && !Pattern.matches(dateValue.regexExpression(), strValue)) {
                        throw new ValidatorException(dateValue.name() + "格式不正确");
                    }

                    // 值域验证
                    if (dateValue.valueRangeEnumClazz() != DataValue.EnumClazz.class) {
                        boolean isExist = false;
                        Method method = null;
                        try {
                            method = dateValue.valueRangeEnumClazz().getMethod(dateValue.valueRangeEnumMethod());
                        } catch (NoSuchMethodException e) {
                            new ValidatorException(e.getMessage());
                        }
                        for (Enum item : (dateValue.valueRangeEnumClazz().getEnumConstants())) {
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
                            throw new ValidatorException(dateValue.name() + "不合法");
                        }
                    }
                }
            }
        }
    }
}
