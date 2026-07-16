package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java 正则表达式（Regular Expression）演示
 *
 * 核心类：java.util.regex
 *  - Pattern  ：编译后的正则对象（线程安全，推荐预编译复用）
 *  - Matcher  ：对输入字符串进行匹配操作的引擎
 *  - String 自带 matches/replaceAll/split 也基于正则
 */
public class Test09 {

    public static void main(String[] args) {
        demoBasic();      // 基础匹配
        demoPredefined(); // 预定义字符类
        demoQuantifier(); // 量词
        demoFind();       // 查找所有匹配
        demoGroup();      // 分组捕获
        demoReplace();    // 替换
        demoSplit();      // 分割
        demoStringAPI();  // String 内置正则方法
        demoEmail();      // 实战：校验邮箱/手机号
    }

    /** 1. 基础匹配 */
    private static void demoBasic() {
        Pattern p = Pattern.compile("hello");
        Matcher m = p.matcher("say hello world");
        System.out.println("=== 基础匹配 ===");
        System.out.println("find 命中 : " + m.find());          // true
        System.out.println("matches(整串) : " + "hello".matches("hello")); // true
        System.out.println("matches(整串需全匹配) : " + "hello!".matches("hello")); // false
    }

    /** 2. 预定义字符类 */
    private static void demoPredefined() {
        System.out.println("\n=== 预定义字符类 ===");
        System.out.println("\\d 数字 : " + "a1b2".matches(".*\\d.*"));     // true
        System.out.println("\\w 单词字符 : " + "abc_123".matches("\\w+")); // true
        System.out.println("\\s 空白 : " + "a b".matches("a\\sb"));        // true
        System.out.println("\\D 非数字 : " + "abc".matches("\\D+"));       // true
        System.out.println(". 任意字符 : " + "a@".matches("a."));            // true
    }

    /** 3. 量词：* + ? {n,m} */
    private static void demoQuantifier() {
        System.out.println("\n=== 量词 ===");
        System.out.println("* 0 或多次 : " + "aaa".matches("a*"));        // true
        System.out.println("+ 1 或多次 : " + "aaa".matches("a+"));        // true
        System.out.println("? 0 或 1 次 : " + "a".matches("a?"));         // true
        System.out.println("{3} 恰好3 : " + "aaa".matches("a{3}"));       // true
        System.out.println("{2,4} 2~4 : " + "aaaa".matches("a{2,4}"));    // true
        System.out.println("{2,} 至少2 : " + "aaaaa".matches("a{2,}"));   // true
    }

    /** 4. 查找所有匹配 */
    private static void demoFind() {
        String text = "订单 1001, 1002, 1003 已发货";
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        System.out.println("\n=== 查找所有匹配 ===");
        while (m.find()) {
            System.out.println("命中 : '" + m.group() + "' at " + m.start() + "~" + m.end());
        }
    }

    /** 5. 分组捕获 (...) */
    private static void demoGroup() {
        // 提取 "姓名:年龄" 中的两部分
        String text = "Tom:18";
        Pattern p = Pattern.compile("(\\w+):(\\d+)");
        Matcher m = p.matcher(text);
        System.out.println("\n=== 分组捕获 ===");
        if (m.matches()) {
            System.out.println("整体 : " + m.group(0));     // Tom:18
            System.out.println("分组1(姓名) : " + m.group(1)); // Tom
            System.out.println("分组2(年龄) : " + m.group(2)); // 18
        }
    }

    /** 6. 替换 */
    private static void demoReplace() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("a1b22c333");
        String r = m.replaceAll("#");
        System.out.println("\n=== 替换 ===");
        System.out.println("replaceAll(\\d+ -> #) : " + r);  // a#b#c#

        // 使用分组反向引用
        String t = "2026-07-11";
        System.out.println("重排日期 : " + t.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})", "$3/$2/$1"));
    }

    /** 7. 分割 */
    private static void demoSplit() {
        String text = "apple, banana ; orange  grape";
        String[] fruits = text.split("[,;\\s]+");   // 逗号/分号/空白 作分隔符
        System.out.println("\n=== 分割 ===");
        for (String f : fruits) System.out.println("  " + f);
    }

    /** 8. String 内置正则方法 */
    private static void demoStringAPI() {
        String s = "abc123def456";
        System.out.println("\n=== String 内置正则 ===");
        System.out.println("replaceAll : " + s.replaceAll("\\d", "*"));      // abc***def***
        System.out.println("replaceFirst : " + s.replaceFirst("\\d+", "#")); // abc#def456
        System.out.println("split('-') : " + String.join("|", "a-b-c".split("-")));
    }

    /** 9. 实战：邮箱 / 手机号校验 */
    private static void demoEmail() {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        String[] emails = {"alice@example.com", "bob@site.cn", "bad@email", "no-at.com"};
        System.out.println("\n=== 实战：邮箱校验 ===");
        for (String e : emails) {
            System.out.println("  " + e + " -> " + emailPattern.matcher(e).matches());
        }

        // 中国手机号：1 开头，第二位是 3-9，共 11 位
        String phoneRegex = "^1[3-9]\\d{9}$";
        System.out.println("=== 实战：手机号校验 ===");
        String[] phones = {"13800138000", "12345678901", "19912345678"};
        for (String ph : phones) {
            System.out.println("  " + ph + " -> " + ph.matches(phoneRegex));
        }
    }
}
