package com.vava.test;

import static org.junit.Assert.assertTrue;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vava.bean.Blue;
import com.vava.bean.RealtimeCallCommandPayload;
import com.vava.bean.Request;
import com.vava.dao.Message;
import com.vava.json.TestParam;
import com.vava.utils.LocalDateAdapter;

/**
 * @author steve
 */
public class LogicTest {

    private static final int DEFAULT_EVENTBUS_THREAD_NUMBER = 2;
    static Map<Character, List<Character>> map = new HashMap<>();

    static {
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('q', 'p', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

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
        assertTrue(isAllDigit("123122345"));
        assertTrue(isAllDigit("1"));
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

    @Test
    public void testThreadLocal() {
        //        ThreadLocal<String> tl = new ThreadLocal<>();
        //        tl.set("qwer");
        //        tl.get();

        ThreadLocal<Integer> tl2 = new ThreadLocal<>();
        tl2.set(2);
        tl2.get();
        WeakReference<ThreadLocal<Integer>> weakReference = new WeakReference<>(tl2);
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
        //
        Thread t1 = Thread.currentThread();
        tl2 = null;
        // 但是实际上map里还有引用

        System.gc();
        System.out.println(weakReference.get());

        Stack<Integer> s = new Stack<>();

    }

    @Test
    public void testCompletableFuture() {
    }

    @Test
    public void testString() {
        String s = "[阿凡达]";
        char a = s.charAt(2);
        System.out.println(a);
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = map.getOrDefault(nums[i], 0) + 1;
            map.put(nums[i], val);

            if (map.get(nums[i]) > (nums.length / 2)) {
                return nums[i];
            }
        }
        return -1;
    }

    public int hammingDistance(int x, int y) {
        int res = x ^ y;
        int cnt = 0;
        while (res != 0) {
            if ((res & 1) == 1) {
                cnt++;
            }
            res = res >> 1;

        }
        return cnt;
    }

    @Test
    public void tt() {
        //        int a = hammingDistance(1,3);
        //        System.out.println(a);
        //        String a = null;
        //        System.out.println(a.toString());
        //        Map<Object, Object> map = new HashMap<>();
        //        String a = null;
        //        String b = "ok";
        ////        map.put(a, b);
        //        System.out.println(map);
        //        System.out.println(map.get(null));
        //        BeanMap beanMap = BeanMap.create(new Blue());
        //        System.out.println(beanMap);
        //        System.out.println(beanMap.get("c"));
        //
        //        Object o = null;
        //        String sv = String.valueOf(o);
        //        System.out.println("ooo:" + sv + ":xxxxxxx:"+sv.charAt(0));


        List<String> res = new ArrayList<>();
        putChar("", "23", 0, res);
        System.out.println(res);


    }

    /**
     * 处理到s的第i个字符，把可能的char放进tmpres。
     */
    private void putChar(String tmpres, String s, int i, List<String> res) {
        if (i == s.length()) {
            res.add(tmpres);
            return;
        }
        Character c = s.charAt(i);
        List<Character> characters = map.get(c);
        for (Character c1 : characters) {
            tmpres += c1;
            putChar(tmpres, s, i + 1, res);
            tmpres = tmpres.substring(0, tmpres.length() - 1);
        }
    }

    @Test
    public void testArr() {
        List<int[]> res = new ArrayList<>();
        res.add(new int[] {1, 2});
        res.add(new int[] {3, 4, 6});
        int[][] ints = res.toArray(new int[0][]);
        int[][] test = new int[0][];
    }

    @Test
    public void testSmoothBursty() throws InterruptedException {
        RateLimiter r = RateLimiter.create(2);
        Long time = System.currentTimeMillis();
        while (true) {
            boolean b = r.tryAcquire();
            if (b) {
                Long diff = System.currentTimeMillis() - time;
                time = System.currentTimeMillis();
                System.out.println("get 1 tokens: " +  diff);
            }
//            Thread.sleep(100);
        }
    }

    public void acquireThread(RateLimiter r) {
        double acquire = r.acquire();
        System.out.println("get 1 tokens: " +  acquire);
    }

    @Test
    public void testAcquire() throws InterruptedException {
        RateLimiter r = RateLimiter.create(2);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> acquireThread(r)).start();
            //            double acquire = r.acquire();
//            System.out.println("get 1 tokens: " +  acquire);
        }
        System.out.println("loop end already!!");
        Thread.sleep(10000);
    }

    @Test
    public void testSort() {
        List<String> res = new ArrayList<>();
        res.add("asdf");
        res.add("a");
        res.add("as");
        res.sort((a, b) -> b.length() - a.length());
        System.out.println(res);
    }

    static class MyThreadLocalMap {

        private MyEntry[] table;

        static class MyEntry extends WeakReference<ThreadLocal<?>> {
            Object value;

            MyEntry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }
    }

    @Test
    public void testComputeIfAbs(){
        Map<Integer, Set<Integer>> map = new HashMap<>();
        map.computeIfAbsent(2, (key)->new HashSet<>());

    }

    final static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
            return new JsonPrimitive(date.format(formatter));
        }
    }

    @Test
    public void testGson() {

        // serializer
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        String json = gson.toJson(Message.ok());
        System.out.println(json);

    }

}
