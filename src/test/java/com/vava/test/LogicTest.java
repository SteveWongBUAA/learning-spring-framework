package com.vava.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.vava.bean.Blue;
import com.vava.bean.RealtimeCallCommandPayload;
import com.vava.bean.Request;
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
        TestParam testParam = new TestParam("type1", new HashMap<String, Object>() {{
            put("a", "a");
            put("1", 1);
        }});
        System.out.println(testParam.toString());

        String tag = "reportMcuResult";
        Map<String, String> extInfo = new HashMap<String, String>() {{
            put("method", tag);
        }};
        extInfo.put("asd", "qwer");
        System.out.println("asd:" + extInfo);
    }

    @Test
    public void testNull() {
        Request request = new Request();
        String id = request.getId();
        System.out.println(id);
    }

    @Test
    public void testCommand() {
        Request request = new Request();
        request.setId("123");
        RealtimeCallCommandPayload realtimeCallCommandPayload = new RealtimeCallCommandPayload(
                "testEventName",
                new HashMap<String, Object>() {{
                    put("field1", "f1");
                    put("field2", 2);
                    put("objectReq", request);
                }}
        );
        System.out.println(realtimeCallCommandPayload.toString());
    }

    @Test
    public void testForeach() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.forEach(iter -> {
            if (iter.equals("2")) {
                return;
            }
            System.out.println(iter);
        });

    }

    @Test
    public void testRegex() {
        Assert.assertTrue(isAllDigit("123122345"));
        Assert.assertTrue(isAllDigit("1"));
        Assert.assertFalse(isAllDigit(""));
        Assert.assertFalse(isAllDigit("1238azzzze11"));
        Assert.assertFalse(isAllDigit("zhcdiusaf"));
        Assert.assertFalse(isAllDigit("zxchfvuasofdh01"));
        Assert.assertFalse(isAllDigit("012x9347980"));
    }

    private Boolean isAllDigit(String text) {
        return Pattern.compile("^\\d+$").matcher(text).find();
    }

    @Test
    public void testIterator() {
        List names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        Iterator iterator1 = names.iterator();
        Iterator iterator2 = names.iterator();
        iterator1.next();
        iterator1.remove();
        iterator2.next(); // 运行结果？
    }

    @Test
    public void testInt() {
        int a;
        int b;
        int c = 3;
        Blue blue = new Blue();
        System.out.println(c);
        System.out.println("before change" + blue);
        changeObject(blue);
        System.out.println("after change" + blue);
    }

    private void changeObject(Blue blue) {
        blue.setA(123);
    }

    @Test
    public void testStream() {
        Long size = 1000000l;
        Map<String, String> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (long i = 0; i < size; i++) {
            list.add(UUID.randomUUID().toString());
        }
        list.parallelStream().forEach(node -> map.put(node, node));
    }

}
