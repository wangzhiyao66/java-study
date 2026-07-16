package org.example;

import java.util.concurrent.CountDownLatch;

/**
 * Java StringBuffer 与 StringBuilder 类演示
 *
 * 区别：
 * - StringBuilder：非线程安全，性能更高（推荐单线程使用）
 * - StringBuffer ：线程安全（方法加 synchronized），性能略低（多线程使用）
 * 两者都继承自 AbstractStringBuilder，内部用可变 char[] 实现
 */
public class Test06 {

    public static void main(String[] args) throws InterruptedException {
        demoCreateAndAppend();
        demoCommonMethods();
        demoCapacity();
        demoPerformance();
        demoThreadSafe();
    }

    /** 1. 创建与拼接 */
    private static void demoCreateAndAppend() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello").append(" ").append("World").append(2026);
        System.out.println("=== 创建与拼接 ===");
        System.out.println("append 结果 : " + sb);          // Hello World2026

        StringBuffer sbf = new StringBuffer("Java");
        sbf.append("-").append("StringBuffer");
        System.out.println("StringBuffer : " + sbf);       // Java-StringBuffer

        // 指定初始容量 / 带初始字符串
        StringBuilder sb2 = new StringBuilder(32);
        StringBuilder sb3 = new StringBuilder("init");
        System.out.println("指定容量构建 : " + sb2 + " / " + sb3);
    }

    /** 2. 常用方法（两者 API 一致） */
    private static void demoCommonMethods() {
        StringBuilder sb = new StringBuilder("HelloWorld");
        System.out.println("\n=== 常用方法 ===");
        System.out.println("length : " + sb.length());                 // 10
        System.out.println("charAt(0) : " + sb.charAt(0));             // H
        System.out.println("insert(5, '-') : " + sb.insert(5, "-"));   // Hello-World
        System.out.println("replace(0,5,'Hi') : " + sb.replace(0, 5, "Hi")); // Hi-World
        System.out.println("delete(2,3) : " + sb.delete(2, 3));        // HiWorld
        System.out.println("reverse : " + sb.reverse());              // dlroW-iH
        sb.reverse();
        System.out.println("再反转回来 : " + sb);
        System.out.println("substring(0,2) : " + sb.substring(0, 2)); // Hi
        sb.setCharAt(0, 'h');
        System.out.println("setCharAt : " + sb);                       // hiWorld
        System.out.println("toString 转 String : " + sb.toString());
    }

    /** 3. 容量与扩容 */
    private static void demoCapacity() {
        StringBuilder sb = new StringBuilder();
        System.out.println("\n=== 容量与扩容 ===");
        System.out.println("默认容量 : " + sb.capacity());    // 16
        sb.append("0123456789012345");                        // 16 个字符
        System.out.println("满 16 后容量 : " + sb.capacity()); // 16
        sb.append("X");                                       // 触发扩容
        System.out.println("再追加后容量 : " + sb.capacity()); // 34 (16*2+2)
        sb.ensureCapacity(100);
        System.out.println("ensureCapacity(100) : " + sb.capacity()); // 100
        sb.trimToSize();
        System.out.println("trimToSize 后容量 : " + sb.capacity());    // 17
    }

    /** 4. 性能对比：StringBuilder 明显快于 StringBuffer */
    private static void demoPerformance() {
        final int n = 100_000;
        long start, end;

        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(i);
        end = System.currentTimeMillis();
        System.out.println("\n=== 性能对比 ===");
        System.out.println("StringBuilder 耗时 : " + (end - start) + " ms");

        start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < n; i++) sbf.append(i);
        end = System.currentTimeMillis();
        System.out.println("StringBuffer  耗时 : " + (end - start) + " ms");
    }

    /** 5. 线程安全演示：StringBuffer 可在多线程下安全累加 */
    private static void demoThreadSafe() throws InterruptedException {
        final int threads = 5, perThread = 1000;
        StringBuffer sbf = new StringBuffer();
        CountDownLatch latch = new CountDownLatch(threads);

        System.out.println("\n=== 线程安全 ===");
        for (int t = 0; t < threads; t++) {
            new Thread(() -> {
                for (int i = 0; i < perThread; i++) sbf.append("a");
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println("StringBuffer 多线程累计长度 : " + sbf.length()); // 5000

        // 对比：StringBuilder 多线程下可能丢数据（非线程安全）
        StringBuilder sb = new StringBuilder();
        CountDownLatch latch2 = new CountDownLatch(threads);
        for (int t = 0; t < threads; t++) {
            new Thread(() -> {
                for (int i = 0; i < perThread; i++) sb.append("a");
                latch2.countDown();
            }).start();
        }
        latch2.await();
        System.out.println("StringBuilder 多线程累计长度（可能 < 5000，演示不安全）: " + sb.length());
    }
}
