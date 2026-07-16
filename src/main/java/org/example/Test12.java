package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 流(Stream) / 文件(File) / IO 演示
 *
 *  - java.io.File           ：文件/目录的抽象路径（增删查、属性）
 *  - java.io 字节/字符流    ：读写磁盘文件
 *  - java.util.stream       ：集合的函数式数据处理（非 IO 流，注意区分）
 */
public class Test12 {

    public static void main(String[] args) throws IOException {
        demoFileApi();
        demoCharIO();      // 字符流：读写文本（推荐文本用这个）
        demoByteIO();      // 字节流：读写二进制
        demoNio();         // NIO 便捷读写
        demoObjectIO();    // 对象序列化
        demoStreamApi();   // 集合 Stream 处理
    }

    /** 1. File 基本 API */
    private static void demoFileApi() throws IOException {
        File dir = new File("demo_tmp");
        if (!dir.exists()) dir.mkdir();                 // 创建目录
        File f = new File(dir, "test.txt");

        System.out.println("=== File API ===");
        System.out.println("创建文件 : " + f.createNewFile());  // true
        System.out.println("是否存在 : " + f.exists());
        System.out.println("是否文件 : " + f.isFile());
        System.out.println("绝对路径 : " + f.getAbsolutePath());
        System.out.println("大小(字节) : " + f.length());

        // 列出当前目录下的 .java 文件
        File root = new File(".");
        String[] javaFiles = root.list((d, name) -> name.endsWith(".java"));
        System.out.println("当前目录 .java 文件数量 : " + (javaFiles == null ? 0 : javaFiles.length));

        f.delete();            // 删除文件
        dir.delete();          // 删除目录
    }

    /** 2. 字符流：FileWriter/BufferedReader 读写文本 */
    private static void demoCharIO() throws IOException {
        String path = "demo_tmp/char.txt";
        new File("demo_tmp").mkdirs();

        // 写
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            bw.write("第一行");
            bw.newLine();
            bw.write("第二行");
        }

        // 读
        System.out.println("\n=== 字符流(文本) ===");
        try (BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  读到 : " + line);
            }
        }
        new File(path).delete();
        new File("demo_tmp").delete();
    }

    /** 3. 字节流：FileInputStream/FileOutputStream 读写二进制 */
    private static void demoByteIO() throws IOException {
        String path = "demo_tmp/data.bin";
        new File("demo_tmp").mkdirs();

        byte[] data = {10, 20, 30, 40};
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(data);
        }

        System.out.println("\n=== 字节流(二进制) ===");
        try (FileInputStream fis = new FileInputStream(path)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = fis.read(buf)) != -1) {
                for (int i = 0; i < len; i++) System.out.print(buf[i] + " ");
            }
            System.out.println();
        }
        new File(path).delete();
        new File("demo_tmp").delete();
    }

    /** 4. NIO 便捷读写（Java 7+ Files） */
    private static void demoNio() throws IOException {
        String path = "demo_tmp/nio.txt";
        new File("demo_tmp").mkdirs();

        List<String> lines = Arrays.asList("hello", "nio", "world");
        Files.write(Paths.get(path), lines, StandardCharsets.UTF_8);

        System.out.println("\n=== NIO (Files) ===");
        List<String> read = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        System.out.println("一次性读出 : " + read);
        new File(path).delete();
        new File("demo_tmp").delete();
    }

    /** 5. 对象序列化：ObjectOutputStream / ObjectInputStream */
    private static void demoObjectIO() throws IOException, ClassNotFoundException {
        String path = "demo_tmp/user.obj";
        new File("demo_tmp").mkdirs();

        User u = new User("Tom", 18);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(u);                       // 对象写入磁盘
        }

        System.out.println("\n=== 对象序列化 ===");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            User back = (User) ois.readObject();      // 读回并反序列化
            System.out.println("反序列化得到 : " + back);
        }
        new File(path).delete();
        new File("demo_tmp").delete();
    }

    /** 6. Stream API：集合的函数式处理（注意：这不是 IO 流） */
    private static void demoStreamApi() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("\n=== Stream API（集合处理） ===");

        // filter + map + collect
        List<Integer> evensSquared = nums.stream()
                .filter(n -> n % 2 == 0)            // 偶数
                .map(n -> n * n)                    // 平方
                .collect(Collectors.toList());
        System.out.println("偶数平方 : " + evensSquared);

        // 求和
        int sum = nums.stream().mapToInt(Integer::intValue).sum();
        System.out.println("总和 : " + sum);

        // 统计
        long count = nums.stream().filter(n -> n > 5).count();
        System.out.println("大于5的个数 : " + count);

        // 排序 + 去重
        List<Integer> mixed = Arrays.asList(3, 1, 2, 3, 1, 5);
        List<Integer> sortedDistinct = mixed.stream()
                .distinct().sorted().collect(Collectors.toList());
        System.out.println("去重排序 : " + sortedDistinct);

        // 数组/文件生成 Stream
        long words = 0;
        try (Stream<String> lines = Files.lines(Paths.get("pom.xml"))) {
            words = lines.count();
        } catch (IOException e) {
            System.out.println("读取 pom.xml 失败 : " + e.getMessage());
        }
        System.out.println("pom.xml 行数 : " + words);
    }

    /** 可序列化的实体类 */
    static class User implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + "}";
        }
    }
}
