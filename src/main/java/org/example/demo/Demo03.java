package org.example.demo;

/**
 * Java 多态（Polymorphism）演示
 *
 * 多态 = 同一操作作用于不同对象，产生不同行为。
 * 两种形式：
 *  - 编译时多态（静态）：方法重载 Overload
 *  - 运行时多态（动态）：方法重写 Override + 父类/接口引用指向子类对象
 *
 * 三大前提：继承 / 重写 / 父类引用指向子类对象（向上转型）
 */
public class Demo03 {

    public static void main(String[] args) {
        demoUpcasting();          // 向上转型
        demoRuntimePoly();        // 运行时多态（核心）
        demoArrayPoly();          // 多态数组
        demoInterfacePoly();      // 接口多态
        demoCompilePoly();        // 编译时多态（重载）
        demoDowncasting();        // 向下转型与 instanceof
    }

    /** 1. 向上转型：子类对象赋值给父类引用（自动，安全） */
    private static void demoUpcasting() {
        System.out.println("=== 向上转型 ===");
        // Animal3 是父类，Dog3/Cat3 是子类
        Animal3 a = new Dog3();   // 子类 -> 父类，自动转换
        System.out.println("a 的实际类型 : " + a.getClass().getSimpleName());
        a.eat();                  // 调用的是 Dog3 重写的 eat
    }

    /** 2. 运行时多态：同一方法调用，不同对象不同表现 */
    private static void demoRuntimePoly() {
        System.out.println("\n=== 运行时多态（核心） ===");
        Animal3 dog = new Dog3();
        Animal3 cat = new Cat3();
        makeEat(dog);             // 传入 dog，执行 Dog3.eat
        makeEat(cat);             // 传入 cat，执行 Cat3.eat
    }

    /** 用父类类型作为参数，可接受任意子类 —— 方法通用、可扩展 */
    private static void makeEat(Animal3 a) {
        a.eat();                  // 实际执行哪个 eat 由运行时对象决定
    }

    /** 3. 多态数组：统一存放不同子类对象 */
    private static void demoArrayPoly() {
        System.out.println("\n=== 多态数组 ===");
        Animal3[] zoo = {new Dog3(), new Cat3(), new Dog3()};
        for (Animal3 a : zoo) {
            a.eat();
        }
    }

    /** 4. 接口多态：引用类型为接口，实现类为具体对象 */
    private static void demoInterfacePoly() {
        System.out.println("\n=== 接口多态 ===");
        Shape circle = new Circle();
        Shape rect = new Rectangle();
        System.out.println("圆面积 : " + circle.area());
        System.out.println("矩形面积 : " + rect.area());
    }

    /** 5. 编译时多态：方法重载（编译期确定） */
    private static void demoCompilePoly() {
        System.out.println("\n=== 编译时多态（重载） ===");
        Printer p = new Printer();
        p.print(123);             // 调用 print(int)
        p.print("hello");         // 调用 print(String)
    }

    /** 6. 向下转型：父类引用转回子类（需强制，且要先 instanceof 判断） */
    private static void demoDowncasting() {
        System.out.println("\n=== 向下转型 ===");
        Animal3 a = new Dog3();
        if (a instanceof Dog3) {          // 先判断，避免 ClassCastException
            Dog3 d = (Dog3) a;            // 强制转换
            d.guard();                    // 调用子类特有方法
        }
    }
}

/** 父类 */
class Animal3 {
    public void eat() {
        System.out.println("动物进食");
    }
}

class Dog3 extends Animal3 {
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    /** 子类特有方法，需向下转型才能调用 */
    public void guard() {
        System.out.println("狗看家");
    }
}

class Cat3 extends Animal3 {
    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}

/** 接口：定义行为契约 */
interface Shape {
    double area();
}

class Circle implements Shape {
    @Override
    public double area() {
        return Math.PI * 3 * 3;   // 半径 3
    }
}

class Rectangle implements Shape {
    @Override
    public double area() {
        return 4 * 5;             // 长 4 宽 5
    }
}

/** 重载演示（编译时多态） */
class Printer {
    void print(int n) {
        System.out.println("打印整数 : " + n);
    }

    void print(String s) {
        System.out.println("打印字符串 : " + s);
    }
}
