package org.example;

/**
 * Java 方法（Method）演示
 *
 * 方法 = 访问修饰符 + 返回类型 + 方法名 + 参数列表 + 方法体
 * 用于封装可复用的代码逻辑。
 */
public class Test10 {

    /** 实例变量（成员变量），方法可访问 */
    private static int classCounter = 0;

    public static void main(String[] args) {
        demoBasic();
        demoReturn();
        demoOverload();
        demoVarargs();
        demoPassByValue();
        demoRecursion();
        demoScope();
    }

    /** 1. 无返回值方法（void）+ 参数 */
    private static void demoBasic() {
        System.out.println("=== 无返回值方法 ===");
        printStar(5);                 // 打印 5 个 *
        greet("Alice");               // 带参方法
    }

    /** void 方法：打印 n 个星号 */
    private static void printStar(int n) {
        for (int i = 0; i < n; i++) System.out.print("*");
        System.out.println();
    }

    /** void 方法：带参问候 */
    private static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    /** 2. 有返回值方法 */
    private static void demoReturn() {
        System.out.println("\n=== 有返回值方法 ===");
        int s = add(3, 5);
        System.out.println("add(3,5) = " + s);
        System.out.println("max(10,20) = " + max(10, 20));
        System.out.println("isEven(4) = " + isEven(4));
        System.out.println("isEven(7) = " + isEven(7));
    }

    private static int add(int a, int b) {
        return a + b;                 // 返回结果给调用者
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    /** 返回 boolean */
    private static boolean isEven(int n) {
        return n % 2 == 0;
    }

    /** 3. 方法重载（Overload）：同名不同参 */
    private static void demoOverload() {
        System.out.println("\n=== 方法重载 ===");
        System.out.println("multiply(2,3) = " + multiply(2, 3));          // 两个 int
        System.out.println("multiply(2.0,3.0) = " + multiply(2.0, 3.0));  // double 版本
        System.out.println("multiply(2,3,4) = " + multiply(2, 3, 4));     // 三个 int
    }

    private static int multiply(int a, int b) {
        return a * b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static int multiply(int a, int b, int c) {
        return a * b * c;
    }

    /** 4. 可变参数（Varargs）：本质是数组 */
    private static void demoVarargs() {
        System.out.println("\n=== 可变参数 ===");
        System.out.println("sum(1,2,3) = " + sum(1, 2, 3));
        System.out.println("sum() = " + sum());            // 0 个参数也合法
        System.out.println("sum(10,20,30,40) = " + sum(10, 20, 30, 40));
    }

    /** 可变参数必须是参数列表最后一个 */
    private static int sum(int... nums) {
        int total = 0;
        for (int n : nums) total += n;
        return total;
    }

    /** 5. 值传递（Java 只有值传递） */
    private static void demoPassByValue() {
        int x = 10;
        System.out.println("\n=== 值传递 ===");
        System.out.println("调用前 x = " + x);
        changeValue(x);
        System.out.println("调用后 x = " + x);   // 仍是 10（基本类型副本）

        int[] arr = {1, 2, 3};
        System.out.println("数组调用前 : " + java.util.Arrays.toString(arr));
        changeArray(arr);                        // 引用地址值传递，但指向同一对象
        System.out.println("数组调用后 : " + java.util.Arrays.toString(arr)); // 内容被改
    }

    private static void changeValue(int v) {
        v = 999;                  // 只修改副本
    }

    private static void changeArray(int[] a) {
        if (a.length > 0) a[0] = 100;   // 通过引用修改原数组元素
    }

    /** 6. 递归方法 */
    private static void demoRecursion() {
        System.out.println("\n=== 递归方法 ===");
        int n = 5;
        System.out.println(n + "! = " + factorial(n));        // 120
        System.out.println("斐波那契(第" + n + "项) = " + fibonacci(n)); // 5
    }

    /** 阶乘：n! = n * (n-1)! */
    private static int factorial(int n) {
        if (n <= 1) return 1;             // 递归终止条件
        return n * factorial(n - 1);
    }

    /** 斐波那契：f(n) = f(n-1) + f(n-2) */
    private static int fibonacci(int n) {
        if (n <= 2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /** 7. 变量作用域 */
    private static void demoScope() {
        System.out.println("\n=== 变量作用域 ===");
        classCounter++;                   // 成员变量
        int local = 1;                    // 局部变量
        {
            int block = 2;                // 块级变量
            System.out.println("局部 + 块级 = " + (local + block));
        }
        // block 此处已不可见
        System.out.println("成员变量 classCounter = " + classCounter);
        System.out.println("局部变量 local = " + local);
    }
}
