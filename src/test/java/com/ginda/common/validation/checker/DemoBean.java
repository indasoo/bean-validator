package com.ginda.common.validation.checker;

import com.ginda.common.validation.RegexType;
import com.ginda.common.validation.ValidateData;

/**
 * Created by Ginda.Tseng on 2016/7/22.
 */
public class DemoBean {

    /* 用户名 */
    @ValidateData(name = "用户名", required = true, valueRangeEnumClazz = UserNameEnum.class, valueRangeEnumMethod = "code")
    private String userName;

    /* 密码 */
    @ValidateData(name = "密码", required = true, minLength = 4, maxLength = 5)
    private String password;

    /* 生日 */
    @ValidateData(name = "生日", regexExpression = "\\d{4}-\\d{2}-\\d{2}")
    private String birthdate;

    /* 邮件地址 */
    @ValidateData(name = "邮件地址", regexType = RegexType.EMAIL)
    private String email;

    /* 年龄 */
    @ValidateData(name = "年龄", required = true, minLength = 1, maxLength = 2, valueRangeEnumClazz = AgeEnum.class, valueRangeEnumMethod = "code")
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
