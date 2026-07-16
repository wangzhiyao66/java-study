package org.example;

import java.io.*;

/**
 * Java 异常处理（Exception Handling）演示
 *
 * 体系：Throwable
 *        ├── Error（严重错误，一般不捕获，如 OutOfMemoryError）
 *        └── Exception
 *              ├── RuntimeException（非受检异常，可不捕获）
 *              └── 受检异常（必须 try-catch 或 throws 声明）
 *
 * 关键字：try / catch / finally / throw / throws
 */
public class Test14 {

    public static void main(String[] args) {
        demoTryCatch();
        demoMultiCatch();
        demoFinally();
        demoTryWithResources();
        demoThrow();
        demoThrows();
        demoCustomException();
        demoExceptionHierarchy();
    }

    /** 1. 基本 try-catch：捕获算术异常 */
    private static void demoTryCatch() {
        System.out.println("=== 基本 try-catch ===");
        try {
            int r = 10 / 0;                 // 抛出 ArithmeticException
            System.out.println(r);
        } catch (ArithmeticException e) {
            System.out.println("捕获异常 : " + e.getMessage());
        }
    }

    /** 2. 多 catch（Java 7+ 可用 | 合并） */
    private static void demoMultiCatch() {
        System.out.println("\n=== 多 catch ===");
        try {
            String s = null;
            s.length();                     // NullPointerException
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获空指针/越界 : " + e.getClass().getSimpleName());
        } catch (Exception e) {            // 兜底，必须放在最后
            System.out.println("其它异常 : " + e);
        }
    }

    /** 3. finally：无论是否异常都会执行（常用于释放资源） */
    private static void demoFinally() {
        System.out.println("\n=== finally ===");
        try {
            System.out.println("try 块执行");
            // int x = 1 / 0;
        } catch (Exception e) {
            System.out.println("catch 块执行");
        } finally {
            System.out.println("finally 块一定执行（释放资源）");
        }
    }

    /** 4. try-with-resources：自动关闭资源（Java 7+） */
    private static void demoTryWithResources() {
        System.out.println("\n=== try-with-resources ===");
        try (BufferedReader br = new BufferedReader(new StringReader("自动关闭示例"))) {
            System.out.println("读到 : " + br.readLine());
        } catch (IOException e) {
            System.out.println("IO 异常 : " + e.getMessage());
        } // 无需手动 close，退出自动关闭
    }

    /** 5. throw：主动抛出异常 */
    private static void demoThrow() {
        System.out.println("\n=== throw 主动抛出 ===");
        try {
            validateAge(15);                // 校验不通过
        } catch (IllegalArgumentException e) {
            System.out.println("捕获 : " + e.getMessage());
        }
    }

    private static void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("年龄 " + age + " 未满 18 岁");
        }
    }

    /** 6. throws：声明方法可能抛出的受检异常 */
    private static void demoThrows() {
        System.out.println("\n=== throws 声明 ===");
        try {
            readFile("不存在的文件.txt");    // 可能抛 IOException
        } catch (IOException e) {
            System.out.println("捕获受检异常 : " + e.getMessage());
        }
    }

    /** 方法声明 throws，把异常交给调用者处理 */
    private static void readFile(String path) throws IOException {
        throw new FileNotFoundException("文件未找到: " + path);
    }

    /** 7. 自定义异常 */
    private static void demoCustomException() {
        System.out.println("\n=== 自定义异常 ===");
        try {
            withdraw(100, 150);             // 余额不足
        } catch (InsufficientBalanceException e) {
            System.out.println("业务异常 : " + e.getMessage() + "，差额=" + e.getShortage());
        }
    }

    private static void withdraw(int balance, int amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("余额不足", amount - balance);
        }
    }

    /** 8. 异常体系与常用方法 */
    private static void demoExceptionHierarchy() {
        System.out.println("\n=== 异常体系 ===");
        try {
            Integer.parseInt("abc");        // NumberFormatException
        } catch (Exception e) {
            System.out.println("异常类型 : " + e.getClass().getName());
            System.out.println("getMessage : " + e.getMessage());
            System.out.println("getLocalizedMessage : " + e.getLocalizedMessage());
            System.out.println("toString : " + e);
            // e.printStackTrace();         // 打印完整堆栈（调试用）
        }
        System.out.println("RuntimeException 是否继承 Exception : " +
                RuntimeException.class.getSuperclass().getSimpleName());
    }

    /** 自定义异常类（继承 Exception 为受检异常） */
    static class InsufficientBalanceException extends Exception {
        private final int shortage;

        public InsufficientBalanceException(String msg, int shortage) {
            super(msg);
            this.shortage = shortage;
        }

        public int getShortage() {
            return shortage;
        }
    }
}
