package org.example.demo;

/**
 * Java 封装（Encapsulation）演示
 *
 * 封装四大原则之一：把数据（字段）和操作数据的方法包在一起，
 * 对外部隐藏实现细节，只通过受控的 public 方法访问/修改。
 *
 * 做法：
 *  - 字段用 private 修饰（数据隐藏）
 *  - 提供 public 的 getter / setter 访问
 *  - 在 setter 中加入校验，保证数据合法
 *  - 配合 this 区分成员变量与参数
 */
public class Demo05 {

    public static void main(String[] args) {
        demoBasic();
        demoValidation();
        demoReadOnly();
    }

    /** 1. 基础封装：private 字段 + getter/setter */
    private static void demoBasic() {
        System.out.println("=== 基础封装 ===");
        Person5 p = new Person5();
        p.setName("Alice");        // 通过 setter 设值
        p.setAge(20);
        System.out.println("姓名 : " + p.getName());   // 通过 getter 取值
        System.out.println("年龄 : " + p.getAge());
    }

    /** 2. 封装的价值：在 setter 中做数据校验 */
    private static void demoValidation() {
        System.out.println("\n=== setter 数据校验 ===");
        Person5 p = new Person5();
        p.setAge(25);
        System.out.println("正常设值后年龄 : " + p.getAge());

        p.setAge(-5);              // 非法值，被 setter 拒绝
        System.out.println("尝试设 -5 后年龄 : " + p.getAge()); // 仍是 25

        p.setName("");             // 非法空名，被拒绝
        System.out.println("尝试设空名后姓名 : '" + p.getName() + "'");
    }

    /** 3. 只读属性：只提供 getter，不提供 setter */
    private static void demoReadOnly() {
        System.out.println("\n=== 只读属性 ===");
        Account acc = new Account("ACC-001", 1000);
        System.out.println("账号(只读) : " + acc.getId());
        System.out.println("余额 : " + acc.getBalance());
        // acc.setId(...) 不存在，外部无法修改账号
    }
}

/** 封装示例：Person */
class Person5 {
    // 1) 字段私有化，外部无法直接访问
    private String name;
    private int age;

    // 2) 提供受控的 getter
    public String getName() {
        return name;
    }

    // 3) setter 中加入校验逻辑
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("  [警告] 姓名不能为空，已忽略");
            return;                 // 不修改字段
        }
        this.name = name;           // this 区分成员变量与参数
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 150) {
            System.out.println("  [警告] 年龄 " + age + " 不合法，已忽略");
            return;
        }
        this.age = age;
    }
}

/** 只读属性示例：账号 id 不可被外部修改 */
class Account {
    private final String id;       // final + 无 setter = 真正只读
    private double balance;

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    // 没有 setId()，外部无法更改账户号
}
