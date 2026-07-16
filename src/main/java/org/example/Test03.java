package org.example;

public class Test03 {
    /**
     * 这节课主要是学数据类型 及 数据类型转换 与 基本运算符
     */

    // 实现main 方法
    public static void main(String[] args) {
        // Java 中有八种原始数据类型
        byte a = -128; // byte: 1子节，范围 -128 到 127
        short b = 32767; // short: 2子节，范围 -32768 到 32767
        int c = 2147483647; // int: 4子节，范围 -2147483648 到 2147483647
        long d = 9223372036854775807L; // long:  注意 long 类型的数值需要加上 L 后缀
        float e = 3.14f; // float: 4子节，表示单精度浮点数，范围较小，精度较低，注意 float 类型的数值需要加上 f 后缀
        double f = 3.141592653589793; // double: 8子节，表示双精度浮点数，范围更大，精度更高
        char g = 'A'; // char: 2子节，表示一个 Unicode 字符，使用单引号括起来, 用于存储单个字符
        boolean h = true; // boolean: 1子节，只有两个值 true 和 false

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println("d: " + d);
        System.out.println("e: " + e);
        System.out.println("f: " + f);
        System.out.println("g: " + g);
        System.out.println("h: " + h);

        /*
         * 在 Java 中，有两种类型的转换：
         * 放大转换（Widening Casting）（自动） - 将较小的类型转换为较大的类型
         * byte -> short -> char -> int -> long -> float -> double
         * 缩小转换（Narrowing Casting）（手动） - 将较大的类型转换为较小的类型
         * double -> float -> long -> int -> char -> short -> byte
         */
        int m = 100;
        double n = m; // 放大转换，自动完成
        System.out.println("n: " + n);
        int o = (int)e;
        System.out.println("o: " + o);

        /*
         * 常见运算符示例：
         * 1) 算术运算符：+ - * / % ++ --
         * 2) 赋值运算符：= += -= *= /= %=
         * 3) 比较运算符：== != > < >= <=
         * 4) 逻辑运算符：&& || !
         * 5) 位运算符：& | ^ ~ << >> >>>
         */
        int q = m--; // 后缀 --：先把 m 的当前值赋给 q，再让 m 自减 1
        System.out.println("q: " + q); // 100
        System.out.println("m: " + m); // 99

        // 1) 算术运算符示例
        int x = 10;
        int y = 3;
        System.out.println("x + y = " + (x + y)); // 13
        System.out.println("x - y = " + (x - y)); // 7
        System.out.println("x * y = " + (x * y)); // 30
        System.out.println("x / y = " + (x / y)); // 3（整数相除会截断小数）
        System.out.println("x % y = " + (x % y)); // 1（取余）

        // 2) 赋值运算符示例
        int score = 80;
        score += 5; // 等价于 score = score + 5
        score *= 2; // 等价于 score = score * 2
        System.out.println("score = " + score); // 170

        // 3) 比较运算符示例（结果是 boolean）
        System.out.println("x > y ? " + (x > y));   // true
        System.out.println("x == y ? " + (x == y)); // false

        // 4) 逻辑运算符示例
        boolean r1 = (x > 5) && (y < 5); // 两边都为 true，结果才为 true
        boolean r2 = (x < 5) || (y < 5); // 任意一边为 true，结果就是 true
        boolean r3 = !(x == y);          // 对结果取反
        System.out.println("r1 = " + r1); // true
        System.out.println("r2 = " + r2); // true
        System.out.println("r3 = " + r3); // true

        // 5) 位运算符示例
        int p = 6;  // 二进制：0110
        int t = 3;  // 二进制：0011
        System.out.println("p & t = " + (p & t)); // 2（二进制 0010）
        System.out.println("p | t = " + (p | t)); // 7（二进制 0111）
        System.out.println("p ^ t = " + (p ^ t)); // 5（二进制 0101）
        System.out.println("p << 1 = " + (p << 1)); // 12（左移一位，相当于乘 2）
        System.out.println("p >> 1 = " + (p >> 1)); // 3（右移一位，相当于除 2）



    }
}
