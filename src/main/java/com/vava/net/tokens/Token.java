package com.vava.net.tokens;

import java.util.regex.Pattern;

/**
 * @author Steve
 * Created on 2020-05
 */
public class Token {
    private static final String pattern = "[a-zA-Z0-9_#\\->\\{\\}\\[\\]\\(\\)]";
    private String strToken;
    private boolean isOp;

    public Token(String strToken, boolean isOp) {
        this.strToken = strToken;
        this.isOp = isOp;
    }

    public static boolean isValidChar(String c) {
        if (c.length() != 1) {
            return false;
        }
        return Pattern.matches(pattern, c);
    }

    public boolean isOp() {
        return isOp;
    }

    public String getStrToken() {
        return strToken;
    }

    @Override
    public String toString() {
        return "`" + strToken + "`";
    }
}
