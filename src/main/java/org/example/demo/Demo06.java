package org.example.demo;

/**
 * Java 接口（Interface）演示
 *
 * 要点：
 *  - 接口是行为的契约（规范），用 interface 定义，方法默认 public abstract
 *  - 类用 implements 实现接口，必须实现所有抽象方法
 *  - 一个类可实现多个接口（弥补 Java 单继承的不足）
 *  - 接口字段默认 public static final（常量）
 *  - Java 8+ 支持 default 方法（带实现，可被子类继承/重写）和 static 方法
 *  - 接口之间可 extends 多继承
 */
public class Demo06 {

    public static void main(String[] args) {
        demoBasic();
        demoMultiInterface();
        demoDefaultStatic();
        demoConstants();
        demoInterfaceInherit();
    }

    /** 1. 基础：实现接口 */
    private static void demoBasic() {
        System.out.println("=== 接口基础 ===");
        Flyable bird = new Bird6();
        bird.fly();                // 调用实现的方法
    }

    /** 2. 一个类实现多个接口（多继承能力的补充） */
    private static void demoMultiInterface() {
        System.out.println("\n=== 实现多个接口 ===");
        SmartPhone phone = new SmartPhone();
        phone.call("10086");       // Phone 接口
        phone.browse("example.com"); // Internet 接口
        phone.takePhoto();         // Camera 接口
    }

    /** 3. default 与 static 方法（Java 8+） */
    private static void demoDefaultStatic() {
        System.out.println("\n=== default / static 方法 ===");
        Vehicle car = new Car6();
        car.run();                 // 抽象方法
        car.stop();                // default 方法，可直接调用
        Vehicle.showType();        // static 方法，用接口名调用

        // default 方法可被重写
        Vehicle bike = new Bike6();
        bike.stop();               // 重写后的 default
    }

    /** 4. 接口常量（public static final） */
    private static void demoConstants() {
        System.out.println("\n=== 接口常量 ===");
        System.out.println("PI = " + MathConst.PI);
        System.out.println("公司 = " + MathConst.COMPANY);
    }

    /** 5. 接口继承接口（多继承） */
    private static void demoInterfaceInherit() {
        System.out.println("\n=== 接口继承 ===");
        Robot r = new Robot();
        r.work();                  // 来自 Workable
        r.charge();                // 来自 Chargeable（被 Advanced 继承合并）
    }
}

/* ---------- 基础接口 ---------- */
interface Flyable {
    void fly();                    // 默认 public abstract
}

class Bird6 implements Flyable {
    @Override
    public void fly() {
        System.out.println("鸟儿扇动翅膀飞翔");
    }
}

/* ---------- 多个接口 ---------- */
interface Phone {
    void call(String number);
}

interface Internet {
    void browse(String url);
}

interface Camera {
    void takePhoto();
}

class SmartPhone implements Phone, Internet, Camera {
    @Override
    public void call(String number) {
        System.out.println("拨打电话 : " + number);
    }

    @Override
    public void browse(String url) {
        System.out.println("浏览网页 : " + url);
    }

    @Override
    public void takePhoto() {
        System.out.println("拍照一张");
    }
}

/* ---------- default / static 方法 ---------- */
interface Vehicle {
    void run();                    // 抽象

    /** default 方法：带默认实现，实现类可直接用，也可重写 */
    default void stop() {
        System.out.println("车辆默认停止");
    }

    /** static 方法：属于接口，用 Vehicle.xxx() 调用 */
    static void showType() {
        System.out.println("这是一辆交通工具");
    }
}

class Car6 implements Vehicle {
    @Override
    public void run() {
        System.out.println("汽车行驶");
    }
    // 未重写 stop()，使用接口默认实现
}

class Bike6 implements Vehicle {
    @Override
    public void run() {
        System.out.println("自行车骑行");
    }

    @Override
    public void stop() {          // 重写 default 方法
        System.out.println("自行车刹闸停止");
    }
}

/* ---------- 接口常量 ---------- */
interface MathConst {
    double PI = 3.14159;          // 默认 public static final
    String COMPANY = "ACME";
}

/* ---------- 接口继承接口 ---------- */
interface Workable {
    void work();
}

interface Chargeable {
    void charge();
}

/** 接口可 extends 多个接口（接口的多继承） */
interface Advanced extends Workable, Chargeable {
}

class Robot implements Advanced {
    @Override
    public void work() {
        System.out.println("机器人工作");
    }

    @Override
    public void charge() {
        System.out.println("机器人充电");
    }
}
