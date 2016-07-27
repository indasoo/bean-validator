package com.ginda.common.validation;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by ginda on 2016/7/27.
 */
public class RegexTypeTest {

    @Test
    public void testEmail() throws Exception {
        Assert.assertTrue(Pattern.matches(RegexType.EMAIL.regex(), "zengyintian@163.com"));
        Assert.assertFalse(Pattern.matches(RegexType.EMAIL.regex(), "zengyintian163.com"));
    }

    @Test
    public void testDateHyphen() throws Exception {
        Assert.assertTrue(Pattern.matches(RegexType.DATE_HYPHEN.regex(), "2016-07-27"));
        Assert.assertFalse(Pattern.matches(RegexType.DATE_HYPHEN.regex(), "2016-07-33"));
        Assert.assertFalse(Pattern.matches(RegexType.DATE_HYPHEN.regex(), "2016/07/33"));
        Assert.assertFalse(Pattern.matches(RegexType.DATE_HYPHEN.regex(), "2016:07:33"));
    }
}