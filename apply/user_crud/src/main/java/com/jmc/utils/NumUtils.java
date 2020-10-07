package com.jmc.utils;

public class NumUtils {
    public static boolean isNumber(String s) {
        char[] cs = s.toCharArray();
        for (char c : cs)
            if (!Character.isDigit(c)) return false;
        return true;
    }
}
