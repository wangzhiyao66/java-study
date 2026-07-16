package org.example.demo.util;

/**
 * 工具类：位于子包 org.example.demo.util
 * 用于演示跨包访问与访问修饰符。
 */
public class StringUtil {

    /** public：任何包都能访问 */
    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /** public：判断是否为空 */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    /** 默认（包私有）访问：只有同包 org.example.demo.util 的类能访问 */
    static String internalTrim(String s) {
        return s == null ? "" : s.trim();
    }
}
