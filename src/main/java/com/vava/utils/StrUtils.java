package com.vava.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steve
 * Created on 2020-05
 */
public class StrUtils {
    private static final char[] CHARS =
            "abcdefghijklmnopqrstuvwxxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890+/".toCharArray();

    public static String convertDigitToBase64(int in) {
        if (in == 0) {
            return String.valueOf(CHARS[0]);
        }
        List<Character> list = new ArrayList<>();
        while (in > 0) {
            int tmp = in & 0b111111;
            list.add(CHARS[tmp]);
            in >>= 6;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
