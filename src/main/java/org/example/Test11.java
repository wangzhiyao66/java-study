package org.example;

/**
 * Java 构造方法（Constructor）演示
 *
 * 构造方法特点：
 *  - 方法名与类名完全相同
 *  - 没有返回类型（连 void 都不写）
 *  - 在 new 对象时自动调用，用于初始化对象
 *  - 支持重载（同名不同参）
 *  - 可用 this() 调用本类其他构造方法（必须位于首行）
 */
public class Test11 {

    public static void main(String[] args) {
        demoConstructor();
    }

    private static void demoConstructor() {
        System.out.println("=== 构造方法演示 ===");

        // 1. 调用无参构造（使用默认值）
        Person p1 = new Person();
        p1.introduce();

        // 2. 调用单参构造
        Person p2 = new Person("Alice");
        p2.introduce();

        // 3. 调用全参构造
        Person p3 = new Person("Bob", 25, "Beijing");
        p3.introduce();

        // 4. 调用静态工厂方法（非构造，但常用）
        Person p4 = Person.createAnonymous();
        p4.introduce();

        System.out.println("\n已创建的对象总数 : " + Person.getCount());
    }
}

/** 演示类：包含多种构造方法 */
class Person {
    private String name;
    private int age;
    private String city;
    private static int count = 0;     // 统计创建个数（属于类，共享）

    /** 1. 无参构造方法：赋默认值 */
    public Person() {
        // 通过 this() 复用全参构造，避免重复代码
        this("Unknown", 0, "Unknown");
        System.out.println("[调用] 无参构造 Person()");
    }

    /** 2. 单参构造：只给名字，其余用默认 */
    public Person(String name) {
        this(name, 0, "Unknown");
        System.out.println("[调用] 单参构造 Person(String)");
    }

    /** 3. 全参构造：完整初始化 */
    public Person(String name, int age, String city) {
        this.name = name;             // this 区分成员变量与参数
        this.age = age;
        this.city = city;
        count++;
        System.out.println("[调用] 全参构造 Person(String,int,String)");
    }

    /** 静态工厂方法（非构造，但常用作创建对象的替代方式） */
    public static Person createAnonymous() {
        return new Person("匿名用户", -1, "未知");
    }

    /** 成员方法 */
    public void introduce() {
        System.out.println("  我是 " + name + "，年龄 " + age + "，来自 " + city);
    }

    public static int getCount() {
        return count;
    }
}
