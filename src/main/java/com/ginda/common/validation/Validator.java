package com.ginda.common.validation;

import com.ginda.common.validation.annotation.DataValue;

import java.lang.reflect.Field;
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
    public static void validate(Object Obj) throws ValidatorException, Exception {

        Field[] fields = Obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 判断字段是否被标记
            if (field.isAnnotationPresent(DataValue.class)) {
                // 设置允许私有方法访问
                field.setAccessible(true);

                DataValue dateValue = field.getAnnotation(DataValue.class);

                Object value = field.get(Obj);

                if (value == null) {
                    // 是否必要检查
                    if (dateValue.required()) {
                        throw new ValidatorException(dateValue.name() + "不能为空");
                    }
                } else {
                    if (dateValue.minLength() != 0 && value.toString().length() < dateValue.minLength()) {
                        throw new ValidatorException(dateValue.name() + "长度不能小于" + dateValue.minLength());
                    }

                    if (dateValue.maxLength() != 0 && value.toString().length() > dateValue.maxLength()) {
                        throw new ValidatorException(dateValue.name() + "长度不能超过" + dateValue.maxLength());
                    }

                    if (!"".equals(dateValue.regexExpression()) && !Pattern.matches(dateValue.regexExpression(), value.toString())) {
                        throw new ValidatorException(dateValue.name() + "格式不正确");
                    }

                    if (dateValue.regexType() != RegexType.NONE) {
                        if (!Pattern.matches(dateValue.regexType().regex(), value.toString())) {
                            throw new ValidatorException(dateValue.name() + "格式不正确");
                        }
                    }

                    if (dateValue.valueRangeEnumClazz() != DataValue.EnumClazz.class) {
                        boolean isExist = false;
                        Method method = dateValue.valueRangeEnumClazz().getMethod(dateValue.valueRangeEnumMethod());
                        for (Enum item : (dateValue.valueRangeEnumClazz().getEnumConstants())) {
                            Object obj = method.invoke(item);

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
