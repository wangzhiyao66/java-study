package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Java Scanner 类演示
 *
 * Scanner 用于解析基本类型和字符串的输入，数据源可以是：
 *  - 键盘输入 System.in
 *  - 字符串 String
 *  - 文件 File / InputStream
 *
 * 常用方法：next()/nextLine()/nextInt()/nextDouble()/hasNextXxx()/useDelimiter()
 */
public class Test13 {

    public static void main(String[] args) throws IOException {
        demoFromString();      // 从字符串读取（最安全、易演示）
        demoFromSystemIn();    // 从键盘读取（交互，按需运行）
        demoFromFile();        // 从文件读取
        demoDelimiter();       // 自定义分隔符
        demoPitfall();         // 经典坑：nextInt 后 nextLine
    }

    /** 1. 从字符串读取 */
    private static void demoFromString() {
        String input = "Tom 18 95.5";
        Scanner sc = new Scanner(input);

        System.out.println("=== 从字符串读取 ===");
        String name = sc.next();              // 读取下一个 token（遇空白结束）
        int age = sc.nextInt();               // 读取 int
        double score = sc.nextDouble();       // 读取 double
        System.out.println("姓名=" + name + ", 年龄=" + age + ", 分数=" + score);

        boolean hasMore = sc.hasNext();       // 是否还有输入
        System.out.println("还有下一个输入 : " + hasMore);
        sc.close();
    }

    /** 2. 从键盘读取（运行时与控制台交互） */
    private static void demoFromSystemIn() {
        System.out.println("\n=== 从键盘读取（输入后回车，输入 exit 退出） ===");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请输入文字(输入 exit 退出): ");
            String line = sc.nextLine();
            if ("exit".equalsIgnoreCase(line)) {
                break;
            }
            System.out.println("  你输入了 : " + line);
        }
        // 注意：键盘输入的 Scanner 通常不在代码里 close，避免关闭 System.in
        // sc.close();
    }

    /** 3. 从文件读取 */
    private static void demoFromFile() throws IOException {
        File f = new File("demo_tmp/scan.txt");
        new File("demo_tmp").mkdirs();
        try (FileWriter fw = new FileWriter(f)) {
            fw.write("apple 3\nbanana 5\ncherry 7\n");
        }

        System.out.println("\n=== 从文件读取 ===");
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String fruit = sc.next();
                int count = sc.nextInt();
                System.out.println("  水果=" + fruit + ", 数量=" + count);
            }
        }
        f.delete();
        new File("demo_tmp").delete();
    }

    /** 4. 自定义分隔符 useDelimiter */
    private static void demoDelimiter() {
        String csv = "1,2,3,4";
        Scanner sc = new Scanner(csv).useDelimiter(",");   // 以逗号分隔
        System.out.println("\n=== 自定义分隔符(逗号) ===");
        while (sc.hasNextInt()) {
            System.out.println("  数字 : " + sc.nextInt());
        }
        sc.close();
    }

    /** 5. 经典坑：nextInt() 之后直接 nextLine() 会读到空行 */
    private static void demoPitfall() {
        String input = "42\nhello";
        Scanner sc = new Scanner(input);
        System.out.println("\n=== 经典坑：nextInt 后 nextLine ===");

        int num = sc.nextInt();              // 读取 42，但换行符 '\n' 仍留在缓冲区
        // String s = sc.nextLine();         // 此时会读到空字符串（吃掉换行符）

        // 正确做法：先消费掉残留的换行符
        sc.nextLine();                       // 消耗换行
        String s = sc.nextLine();            // 再读真正的下一行
        System.out.println("数字=" + num + ", 下一行=" + s);
        sc.close();
    }
}
