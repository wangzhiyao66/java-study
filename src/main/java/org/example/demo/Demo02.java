package org.example.demo;

/**
 * Java 重写(Override) 与 重载(Overload) 演示
 *
 * 区别速记：
 *  ┌──────────┬───────────────────────┬───────────────────────┐
 *  │          │ 重写 Override          │ 重载 Overload          │
 *  ├──────────┼───────────────────────┼───────────────────────┤
 *  │ 发生位置 │ 父子类之间             │ 同一个类中             │
 *  │ 方法名   │ 相同                  │ 相同                  │
 *  │ 参数列表 │ 必须完全相同           │ 必须不同（类型/个数/顺序）│
 *  │ 返回类型 │ 相同或其子类（协变）   │ 无要求                 │
 *  │ 访问修饰 │ 不能更严格             │ 无要求                 │
 *  │ 异常     │ 不能抛出更宽泛检查异常 │ 无要求                 │
 *  │ 绑定时机 │ 运行时（动态绑定）     │ 编译时（静态绑定）     │
 *  └──────────┴───────────────────────┴───────────────────────┘
 */
public class Demo02 {

    public static void main(String[] args) {
        demoOverload();
        demoOverride();
        demoCompare();
    }

    /** 1. 重载 Overload：同一个类中，方法名相同、参数列表不同 */
    private static void demoOverload() {
        System.out.println("=== 重载 Overload（同一类内） ===");
        Calculator calc = new Calculator();

        System.out.println("add(1,2)        = " + calc.add(1, 2));            // int,int
        System.out.println("add(1.5,2.5)    = " + calc.add(1.5, 2.5));        // double,double
        System.out.println("add(1,2,3)      = " + calc.add(1, 2, 3));         // 三个参数
        System.out.println("add(\"a\",\"b\")   = " + calc.add("a", "b"));      // 不同类型
        // 以上四个 add 互为重载：仅参数类型/数量不同，返回值可不同
    }

    /** 2. 重写 Override：子类重新实现父类方法 */
    private static void demoOverride() {
        System.out.println("\n=== 重写 Override（父子类间） ===");
        Animal2 a1 = new Dog2();
        Animal2 a2 = new Cat2();
        a1.sound();        // 运行时根据实际对象执行 Dog2.sound()
        a2.sound();        // 执行 Cat2.sound()
    }

    /** 3. 对比总结 */
    private static void demoCompare() {
        System.out.println("\n=== 对比总结 ===");
        System.out.println("重载：编译期决定调用哪个（静态绑定），解决'同名不同参'");
        System.out.println("重写：运行期决定调用哪个（动态绑定），解决'子类改父类行为'");
    }
}

/** 重载演示类 */
class Calculator {
    // 以下方法名都是 add，但参数列表不同 -> 重载
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    String add(String a, String b) {
        return a + b;
    }

    // 仅返回值不同、参数相同 -> 不是重载，编译报错（已注释）：
    // int add(int a, int b) { return a + b; }
}

/** 重写演示：父类 */
class Animal2 {
    public void sound() {
        System.out.println("动物发出声音");
    }
}

/** 重写演示：子类 Dog2 */
class Dog2 extends Animal2 {
    /** 方法签名（名+参数）与父类完全一致 -> 重写 */
    @Override
    public void sound() {
        System.out.println("狗：汪汪汪");
    }
}

/** 重写演示：子类 Cat2 */
class Cat2 extends Animal2 {
    @Override
    public void sound() {
        System.out.println("猫：喵喵喵");
    }
}
