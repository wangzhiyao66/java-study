package org.example.demo;

/**
 * Java 继承（Inheritance）演示
 *
 * 要点：
 *  - 用 extends 关键字实现继承，子类复用父类成员
 *  - Java 只支持单继承（一个类只能有一个直接父类）
 *  - 所有类最终都继承自 Object
 *  - 子类构造方法默认（或显式通过 super()）先调用父类构造方法
 *  - 子类可重写（@Override）父类方法，实现多态
 *  - super 关键字用于访问父类成员 / 构造方法
 */
public class Demo01 {

    public static void main(String[] args) {
        demoBasic();
        demoOverride();
        demoPolymorphism();
        demoSuperCtor();
        demoObject();
    }

    /** 1. 继承的基本使用：复用父类字段与方法 */
    private static void demoBasic() {
        System.out.println("=== 继承基础 ===");
        Dog dog = new Dog();
        dog.name = "旺财";            // 继承自 Animal 的字段
        dog.eat();                    // 继承自 Animal 的方法
        dog.bark();                   // Dog 自己的方法
    }

    /** 2. 方法重写（Override） */
    private static void demoOverride() {
        System.out.println("\n=== 方法重写 ===");
        Cat cat = new Cat();
        cat.name = "咪咪";
        cat.speak();                  // 调用 Cat 重写后的 speak()
    }

    /** 3. 多态：父类引用指向子类对象 */
    private static void demoPolymorphism() {
        System.out.println("\n=== 多态 ===");
        Animal a1 = new Dog();
        Animal a2 = new Cat();
        a1.speak();                   // 运行时根据实际对象调用 Dog.speak
        a2.speak();                   // 调用 Cat.speak
        // 编译看左边（Animal），运行看右边（实际对象）
    }

    /** 4. super 调用父类构造方法 */
    private static void demoSuperCtor() {
        System.out.println("\n=== super 调用父类构造 ===");
        // 创建子类时，会先调用父类构造（super() 默认隐式存在）
        Bird bird = new Bird("小翠", "麻雀");
        bird.show();
    }

    /** 5. 所有类都继承自 Object */
    private static void demoObject() {
        System.out.println("\n=== 继承自 Object ===");
        Dog d = new Dog();
        System.out.println("toString : " + d.toString());    // Object 的 toString
        System.out.println("hashCode : " + d.hashCode());
        System.out.println("getClass : " + d.getClass().getName());
        System.out.println("是否同一对象 : " + d.equals(d)); // Object 的 equals
    }
}

/** 父类 */
class Animal {
    String name;

    public Animal() {
        System.out.println("[Animal] 无参构造");
    }

    public void eat() {
        System.out.println(name + " 在吃东西");
    }

    /** 可被重写的方法 */
    public void speak() {
        System.out.println(name + " 发出声音");
    }
}

/** 子类 Dog 继承 Animal */
class Dog extends Animal {
    public void bark() {
        System.out.println(name + " 汪汪叫");
    }

    /** 重写父类 speak */
    @Override
    public void speak() {
        System.out.println(name + " 汪汪汪");
    }
}

/** 子类 Cat 继承 Animal */
class Cat extends Animal {
    /** 重写父类 speak */
    @Override
    public void speak() {
        System.out.println(name + " 喵喵喵");
    }
}

/** 子类 Bird：演示 super(...) 调用父类带参构造 */
class Bird extends Animal {
    private String species;

    /** 通过 super(name) 先初始化父类字段 */
    public Bird(String name, String species) {
        super();                 // 调用父类无参构造（可省略，默认存在）
        this.name = name;        // 父类字段
        this.species = species;
        System.out.println("[Bird] 带参构造");
    }

    public void show() {
        // super 访问父类方法
        super.eat();
        System.out.println("种类 : " + species);
    }
}
