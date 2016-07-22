package com.ginda.common.security.handler;

import com.ginda.common.security.annotation.SecurityField;

import java.lang.reflect.Field;

/**
 * Created by Ginda.Tseng on 2016/7/22.
 */
public class SecurityDataHandler {

    /**
     * 数据
     * @param obj
     * @param key
     * @return
     */
    public static void handle(Object obj, String key) throws IllegalAccessException {

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 判断字段是否被标记
            if (field.isAnnotationPresent(SecurityField.class)) {
                // 设置允许私有方法访问
                field.setAccessible(true);

                // 判断是否为空
                if (field.get(obj) != null) {
                    // 加密数据解密 TODO
//                    String data = AES.decrypt(field.get(ob).toString(), key);
                    String data = "";
                    field.set(obj, data);
                }
            }
        }
    }
}
