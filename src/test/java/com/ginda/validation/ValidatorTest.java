package com.ginda.validation;

import junit.framework.TestCase;

/**
 * Created by Ginda.Tseng on 2016/7/28.
 */
public class ValidatorTest extends TestCase {

    public void testValidate() throws Exception {
        ValidateBean demoBean = new ValidateBean();
        demoBean.setUserName("admin");
        demoBean.setPassword("12345");
        demoBean.setBirthdate("1986-04-01");
        demoBean.setEmail("zengyintian@163.com");
        demoBean.setType(2);

        ValidateBean relateData = new ValidateBean();
        relateData.setUserName("test");
        relateData.setPassword("56789");
        relateData.setBirthdate("1986-04-01");
        relateData.setEmail("zengyintian@163.com");
        relateData.setType(3);
        demoBean.setData(relateData);

        Validator.validate(demoBean);
    }
}