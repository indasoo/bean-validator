package com.ginda.common.validation.checker;

import com.ginda.common.validation.annotation.RequiredValidator;

/**
 * Created by Ginda.Tseng on 2016/7/22.
 */
public class DemoBean {

    /* 用户名 */
    @RequiredValidator(required = true, description = "用户名不能为空")
    private String userName;

    /* 密码 */
    @RequiredValidator(required = true, description = "密码不能为空")
    private String password;
}
