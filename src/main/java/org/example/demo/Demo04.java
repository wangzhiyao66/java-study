package org.example.demo;

/**
 * Java 抽象类（Abstract Class）演示
 *
 * 要点：
 *  - 用 abstract 修饰的类叫抽象类，不能 new 实例化
 *  - 用 abstract 修饰的方法叫抽象方法，只有声明没有实现，必须由其子类重写
 *  - 含有抽象方法的类必须声明为抽象类
 *  - 抽象类可以包含具体方法、构造方法、字段（比接口更丰富）
 *  - 子类必须实现所有抽象方法，否则子类也要声明为 abstract
 *  - 常用于抽取子类公共代码 + 定义统一规范
 */
public class Demo04 {

    public static void main(String[] args) {
        demoBasic();
        demoTemplate();
        demoAbstractField();
    }

    /** 1. 基本使用：子类实现抽象方法 */
    private static void demoBasic() {
        System.out.println("=== 抽象类基础 ===");
        Animal4 dog = new Dog4();
        dog.setName("旺财");
        dog.speak();              // 子类实现的抽象方法
        dog.breathe();            // 父类具体方法（公共行为）
    }

    /** 2. 模板方法模式：抽象类定义骨架，子类填充细节 */
    private static void demoTemplate() {
        System.out.println("\n=== 模板方法模式 ===");
        Game game1 = new Chess();
        Game game2 = new LOL();
        game1.play();             // 共用流程，各步骤由子类实现
        game2.play();
    }

    /** 3. 抽象类可带字段、构造方法、具体方法 */
    private static void demoAbstractField() {
        System.out.println("\n=== 抽象类字段/构造 ===");
        // 抽象类构造在子类 new 时被调用（用于初始化公共字段）
        Employee emp = new Manager("张三", 8000);
        emp.showInfo();
    }
}

/** 抽象类：动物 */
abstract class Animal4 {
    protected String name;          // 普通字段

    public void setName(String name) {
        this.name = name;
    }

    /** 抽象方法：由子类决定如何发声 */
    public abstract void speak();

    /** 具体方法：所有子类共用 */
    public void breathe() {
        System.out.println(name + " 用肺呼吸");
    }
}

class Dog4 extends Animal4 {
    /** 必须实现抽象方法，否则编译报错 */
    @Override
    public void speak() {
        System.out.println(name + " 汪汪汪");
    }
}

/**
 * 模板方法模式：
 * 抽象类定义算法骨架（play），把可变的步骤声明为抽象方法。
 */
abstract class Game {
    /** 模板方法：final 防止子类破坏整体流程 */
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }

    private void initialize() {
        System.out.println("[模板] 游戏初始化");
    }

    abstract void startPlay();     // 子类实现

    abstract void endPlay();       // 子类实现
}

class Chess extends Game {
    @Override
    void startPlay() {
        System.out.println("  国际象棋：开局摆子");
    }

    @Override
    void endPlay() {
        System.out.println("  国际象棋：将死获胜");
    }
}

class LOL extends Game {
    @Override
    void startPlay() {
        System.out.println("  LOL：进入召唤师峡谷");
    }

    @Override
    void endPlay() {
        System.out.println("  LOL：推掉水晶获胜");
    }
}

/** 抽象类带字段与构造方法 */
abstract class Employee {
    protected String name;
    protected double salary;

    /** 抽象类可以有构造方法（供子类 super 调用） */
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        System.out.println("[Employee] 构造，初始化公共字段");
    }

    public void showInfo() {
        System.out.println("员工：" + name + "，月薪：" + salary);
    }

    public abstract double calcAnnual();   // 计算年薪（子类不同）
}

class Manager extends Employee {
    public Manager(String name, double salary) {
        super(name, salary);              // 调用父类构造
    }

    @Override
    public double calcAnnual() {
        return salary * 12 + 5000;        // 经理年终奖 5000
    }
}
