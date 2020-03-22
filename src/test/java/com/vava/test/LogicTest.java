package com.vava.test;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.vava.json.TestParam;

/**
 * @author steve
 */
public class LogicTest {

    private static final int DEFAULT_EVENTBUS_THREAD_NUMBER = 2;

    @Test
    public void test01() {
        String ver = "asdf-we-qweza";
        Assert.assertEquals("asdf", ver.split("-")[0]);
        ver = "qqqe";
        Assert.assertEquals("qqqe", ver.split("-")[0]);
    }

    @Test
    public void TestJson() {
        TestParam testParam= new TestParam("type1", new HashMap<String, Object>() {{
            put("a", "a");
            put("1", 1);
        }});
        System.out.println(testParam.toString());
    }
}
