package org.example;

public class Test01 {
    /**
     * 今天主要是学习了 java的输出语句，单行注释，多行注释，方法调用
     * 输出语句分为： print (打印) 和 println (打印并换行) 两种，
     * 方法调用： main 方法是 java 程序的入口方法，且必须存在
     */
    public static void main (String[] args) {
        System.out.println("Hello World!, 我是一个java 初学者，");
        System.out.println("这是我的第一个应用， 初学类 ，public static Main 方法");
        System.out.println("子类： public static void text01( String[] args ){}");
        // 调用 text01 方法，传入一个参数，输出参数文本
        text01(args);
    }

    /**
     * 这个是我的第一个java 方法，方法名是 text01, 这是一个静态公共的方法，参数是一个字符串数组
     * @param args 参数是一个字符串数组
     */
    public static void text01 (String[] args) {
        System.out.println("这是一个静态公共的方法, 方法名是 text01");
        System.out.print("day: ");
        System.out.print("2026-06-14 \n");
        System.out.print("今年是");
        System.out.print(1013 * 2 + 3);
        System.out.print("年 \n");
    }
}
