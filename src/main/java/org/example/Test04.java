package org.example;

public class Test04 {
    public static void main(String[] args) {
        // Character 是对基本类型 char 的包装类，常用于字符判断与转换。
        char a = 'A';
        char b = 'b';
        char digit = '8';
        char space = ' ';
        char chinese = '中';

        System.out.println("=== Character 常用判断方法 ===");
        printCheck(a);
        printCheck(b);
        printCheck(digit);
        printCheck(space);
        printCheck(chinese);

        System.out.println("\n=== Character 常用转换方法 ===");
        System.out.println("toLowerCase('A') = " + Character.toLowerCase(a));
        System.out.println("toUpperCase('b') = " + Character.toUpperCase(b));

        System.out.println("\n=== 其他常见方法 ===");
        System.out.println("isLetterOrDigit('中') = " + Character.isLetterOrDigit(chinese));
        System.out.println("getNumericValue('8') = " + Character.getNumericValue(digit));

        // 自动装箱：char -> Character
        Character boxed = a;
        // 拆箱：Character -> char
        char unboxed = boxed;
        System.out.println("\n装箱后对象: " + boxed + ", 拆箱后字符: " + unboxed);
    }

    private static void printCheck(char c) {
        System.out.println("字符: '" + c + "'");
        System.out.println("  isLetter      = " + Character.isLetter(c));
        System.out.println("  isDigit       = " + Character.isDigit(c));
        System.out.println("  isWhitespace  = " + Character.isWhitespace(c));
        System.out.println("  isUpperCase   = " + Character.isUpperCase(c));
        System.out.println("  isLowerCase   = " + Character.isLowerCase(c));
    }
}
