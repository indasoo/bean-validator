package com.ginda.common.validation;

/**
 * Created by Ginda.Tseng on 2016/7/28.
 */
public class ValidateBean {

    /* 用户名 */
    @ValidateData(name = "用户名", required = true, valueRangeEnumClazz = UserNameEnum.class, valueRangeEnumMethod = "code")
    private String userName;

    /* 密码 */
    @ValidateData(name = "密码", required = true, minLength = 4, maxLength = 5)
    private String password;

    /* 生日 */
    @ValidateData(name = "生日", regexExpression = "\\d{4}-\\d{2}-\\d{2}", regexType = RegexType.DATE_HYPHEN)
    private String birthdate;

    /* 邮件地址 */
    @ValidateData(name = "邮件地址", required = false)
    private String email;

    /* 类型 */
    @ValidateData(name = "类型", required = true, minLength = 1, maxLength = 2, valueRangeEnumClazz = TypeEnum.class, valueRangeEnumMethod = "code")
    private int type;

    /* 关联数据 */
    @ValidateData(name = "关联数据", required = false)
    private ValidateBean data;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ValidateBean getData() {
        return data;
    }

    public void setData(ValidateBean data) {
        this.data = data;
    }
}
