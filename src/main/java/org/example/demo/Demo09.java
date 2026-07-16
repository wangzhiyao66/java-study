package org.example.demo;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Java 反射（Reflection）演示
 *
 * 反射允许程序在运行时：
 *  - 获取类的结构信息（构造方法、字段、方法、注解）
 *  - 动态创建对象、访问/修改字段、调用方法
 * 常用于框架（Spring、MyBatis）、序列化、依赖注入等。
 *
 * 获取 Class 对象的三种方式：类名.class / 对象.getClass() / Class.forName(全限定名)
 */
public class Demo09 {

    public static void main(String[] args) throws Exception {
        demoGetClass();
        demoInspect();
        demoCreateInstance();
        demoAccessField();
        demoInvokeMethod();
        demoAnnotation();
    }

    /** 1. 获取 Class 对象的三种方式 */
    private static void demoGetClass() throws ClassNotFoundException {
        System.out.println("=== 获取 Class 对象 ===");
        Class<?> c1 = User.class;                       // 方式1：类名.class
        Class<?> c2 = new User().getClass();            // 方式2：对象.getClass()
        Class<?> c3 = Class.forName("org.example.demo.User"); // 方式3：全限定名
        System.out.println("c1 == c2 == c3 : " + (c1 == c2 && c2 == c3)); // 同一 Class
    }

    /** 2. 探查类结构：构造方法、字段、方法 */
    private static void demoInspect() {
        System.out.println("\n=== 探查类结构 ===");
        Class<?> c = User.class;

        System.out.println("类名 : " + c.getSimpleName());
        System.out.println("包名 : " + c.getPackageName());
        System.out.println("父类 : " + c.getSuperclass().getSimpleName());

        System.out.println("构造方法 : ");
        for (Constructor<?> con : c.getDeclaredConstructors()) {
            System.out.println("  " + con);
        }

        System.out.println("字段 : ");
        for (Field f : c.getDeclaredFields()) {
            System.out.println("  " + Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName());
        }

        System.out.println("方法 : ");
        for (Method m : c.getDeclaredMethods()) {
            System.out.println("  " + m.getName() + "()");
        }
    }

    /** 3. 动态创建对象（调用构造方法） */
    private static void demoCreateInstance() throws Exception {
        System.out.println("\n=== 动态创建对象 ===");
        Class<?> c = User.class;
        Constructor<?> con = c.getConstructor(String.class, int.class);
        User u = (User) con.newInstance("反射创建", 99);   // 等价 new User("反射创建",99)
        System.out.println("创建结果 : " + u);
    }

    /** 4. 访问/修改私有字段（突破 private） */
    private static void demoAccessField() throws Exception {
        System.out.println("\n=== 访问私有字段 ===");
        User u = new User("Tom", 18);
        Field f = User.class.getDeclaredField("name");
        f.setAccessible(true);          // 关闭访问检查，才能改 private 字段
        System.out.println("修改前 name : " + f.get(u));
        f.set(u, "Jerry");
        System.out.println("修改后 name : " + f.get(u));
    }

    /** 5. 动态调用方法 */
    private static void demoInvokeMethod() throws Exception {
        System.out.println("\n=== 动态调用方法 ===");
        User u = new User("Alice", 20);
        Method m = User.class.getDeclaredMethod("sayHello");
        m.setAccessible(true);
        m.invoke(u);                    // 等价 u.sayHello()

        // 调用带参方法
        Method m2 = User.class.getDeclaredMethod("greet", String.class);
        m2.invoke(u, "World");
    }

    /** 6. 读取注解信息 */
    private static void demoAnnotation() throws Exception {
        System.out.println("\n=== 读取注解 ===");
        Class<?> c = User.class;
        if (c.isAnnotationPresent(Table.class)) {
            Table t = c.getAnnotation(Table.class);
            System.out.println("@Table name = " + t.name());
        }
        for (Method m : c.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Log.class)) {
                System.out.println("方法 " + m.getName() + " 被 @Log 标记");
            }
        }
    }
}

/** 被反射探查的目标类 */
@Table(name = "t_user")
class User {
    private String name;
    private int age;

    public User() {}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void sayHello() {
        System.out.println("  Hello, 我是 " + name);
    }

    @Log
    private void greet(String who) {
        System.out.println("  " + name + " 向 " + who + " 打招呼");
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}

/** 自定义类注解 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)   // 运行时保留，反射才能读到
@interface Table {
    String name();
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Log {
}
