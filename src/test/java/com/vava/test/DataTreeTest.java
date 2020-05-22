package com.vava.test;

import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.vava.datatree.DataTree;
import com.vava.utils.StrUtils;

/**
 * @author Steve
 * Created on 2020-05
 */
public class DataTreeTest {

    @Test
    public void test01() {
        DataTree dataTree = new DataTree();
        for (int i = 0; i < 64; i++) {
            String key = StrUtils.convertDigitToBase64(i);
            dataTree.addData(key, "root");
        }
        for (int i = 64; i < 99999999; i += 16) {
            String key = StrUtils.convertDigitToBase64(i);
            //            System.out.println(key);
            if (Math.random() < (1.0d / key.length())) {
                // 第2层有1/2的概率生成，第3层有1/3概率，如此类推
                String pkey = key.substring(0, key.length() - 1);
                dataTree.addData(key, pkey);
            }
        }
        Map<String, Object> dataNodeMap1 = dataTree.getAllChildrenHierarchically("root");
        String dn1 = new Gson().toJson(dataNodeMap1);
        System.out.println(dn1);
    }

    @Test
    public void test02() {
        DataTree dataTree = new DataTree();
        for (int i = 0; i < 10; i++) {
            dataTree.addData(String.valueOf(i), "root");
        }
        for (int i = 10; i < 999999; i += 3) {
            String key = String.valueOf(i);
            if (Math.random() < (1.0d / key.length())) {
                // 第2层有1/2的概率生成，第3层有1/3概率，如此类推
                String pkey = key.substring(0, key.length() - 1);
                dataTree.addData(key, pkey);
            }
        }
        Map<String, Object> dataNodeMap1 = dataTree.getAllChildrenHierarchically("root");
        String dn1 = new Gson().toJson(dataNodeMap1);
        System.out.println(dn1);
    }

    @Test
    public void testBase64() {
        for (int i = 0; i < 999; i++) {
            System.out.println(i + " " + StrUtils.convertDigitToBase64(i));
        }
    }
}
