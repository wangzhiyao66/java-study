package org.example;

import java.util.Arrays;

/**
 * Java String 类常用方法演示
 */
public class Test05 {

    public static void main(String[] args) {
        demoCreation();
        demoCommonMethods();
        demoImmutable();
        demoCompare();
        demoSubstringAndSplit();
        demoTransform();
        demoBuilder();
    }

    /** 1. 创建 String 的几种方式 */
    private static void demoCreation() {
        String s1 = "hello";              // 字面量（常量池）
        String s2 = new String("hello");  // new 对象（堆）
        String s3 = "he" + "llo";         // 编译期拼接，仍是常量池
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        String s4 = new String(chars);    // 由字符数组构造

        System.out.println("=== 创建方式 ===");
        System.out.println("s1 == s2 : " + (s1 == s2));        // false
        System.out.println("s1.equals(s2) : " + s1.equals(s2)); // true
        System.out.println("s1 == s3 : " + (s1 == s3));        // true
        System.out.println("s4 : " + s4);
    }

    /** 2. 常用基础方法 */
    private static void demoCommonMethods() {
        String s = "Hello World";
        System.out.println("\n=== 基础方法 ===");
        System.out.println("length : " + s.length());          // 11
        System.out.println("charAt(0) : " + s.charAt(0));      // H
        System.out.println("indexOf('o') : " + s.indexOf('o'));// 4
        System.out.println("lastIndexOf('o') : " + s.lastIndexOf('o')); // 7
        System.out.println("isEmpty : " + s.isEmpty());        // false
        System.out.println("contains('World') : " + s.contains("World")); // true
    }

    /** 3. String 不可变特性 */
    private static void demoImmutable() {
        String s = "abc";
        String t = s.toUpperCase();
        System.out.println("\n=== 不可变性 ===");
        System.out.println("原字符串 s : " + s);   // abc（未改变）
        System.out.println("新字符串 t : " + t);   // ABC
        System.out.println("s == t : " + (s == t)); // false
    }

    /** 4. 比较方法 */
    private static void demoCompare() {
        String a = "hello";
        String b = "HELLO";
        System.out.println("\n=== 比较方法 ===");
        System.out.println("equals : " + a.equals(b));              // false
        System.out.println("equalsIgnoreCase : " + a.equalsIgnoreCase(b)); // true
        System.out.println("compareTo : " + a.compareTo("world")); // 负数
        System.out.println("startsWith : " + a.startsWith("he"));   // true
        System.out.println("endsWith : " + a.endsWith("lo"));       // true
    }

    /** 5. 截取与拆分 */
    private static void demoSubstringAndSplit() {
        String s = "Java,Python,C++,Go";
        System.out.println("\n=== 截取与拆分 ===");
        System.out.println("substring(5) : " + s.substring(5));          // Python,C++,Go
        System.out.println("substring(0,4) : " + s.substring(0, 4));     // Java
        System.out.println("replace : " + s.replace("C++", "CSharp"));
        String[] parts = s.split(",");
        System.out.println("split : " + Arrays.toString(parts));
        System.out.println("trim : '" + "  hi  ".trim() + "'");
    }

    /** 6. 大小写与转换 */
    private static void demoTransform() {
        String s = "  Mixed CASE 123  ";
        System.out.println("\n=== 转换 ===");
        System.out.println("toUpperCase : " + s.toUpperCase());
        System.out.println("toLowerCase : " + s.trim().toLowerCase());
        System.out.println("toCharArray : " + Arrays.toString(s.trim().toCharArray()));
        System.out.println("valueOf(int) : " + String.valueOf(123));
        System.out.println("join : " + String.join("-", "a", "b", "c"));
    }

    /** 7. StringBuilder 可变字符串拼接 */
    private static void demoBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello").append(" ").append("World").reverse();
        System.out.println("\n=== StringBuilder ===");
        System.out.println("result : " + sb);                 // dlroW olleH
        System.out.println("链式后反转 : " + new StringBuilder("abc").reverse()); // cba

        StringBuffer sbf = new StringBuffer("thread");
        sbf.append("-safe");
        System.out.println("StringBuffer : " + sbf);
    }
}
