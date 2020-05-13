package com.vava.net.tokens;

import java.util.regex.Pattern;

/**
 * @author Steve
 * Created on 2020-05
 */
@Deprecated
public abstract class AbstractToken {
    private static final String pattern = "[a-zA-Z0-9_\\->\\{\\}\\[\\]\\(\\)]";
    private String strToken;

    public static boolean isValidChar(String c) {
        if (c.length() != 1) {
            return false;
        }
        return Pattern.matches(pattern, c);
    }
}
