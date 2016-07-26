package com.ginda.common.validation.checker;

import com.ginda.common.validation.Validator;
import org.junit.Test;

/**
 * Created by Ginda.Tseng on 2016/7/22.
 */
public class DemoCheckerTest {

    @Test
    public void testValidate() throws Exception {
        DemoBean demoBean = new DemoBean();
        demoBean.setUserName("admin");
        demoBean.setPassword("12345");
        demoBean.setBirthdate("1986-04-01");
        demoBean.setEmail("zengyintian@163.com");
        Validator.validate(demoBean);
    }
}