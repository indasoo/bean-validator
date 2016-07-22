package com.ginda.common.validation.checker;

import com.ginda.common.validation.annotation.RequiredValidator;

import java.lang.reflect.Field;

/**
 * Created by Ginda.Tseng on 2016/7/22.
 */
public class DemoChecker {

    /**
     * 数据验证
     *
     * @param Obj 数据
     * @return
     */
    public static String validate(Object Obj) throws IllegalAccessException {

        Field[] fields = Obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 判断字段是否被标记
            if (field.isAnnotationPresent(RequiredValidator.class)) {
                // 设置允许私有方法访问
                field.setAccessible(true);

                RequiredValidator requiredValidator = field.getAnnotation(RequiredValidator.class);

                // 是否必要检查
                if (requiredValidator.required() && field.get(Obj) == null) {
                   return requiredValidator.description();
                }
            }
        }

        return null;
    }
}
