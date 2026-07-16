package org.example.demo;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Java 枚举（enum）演示
 *
 * 要点：
 *  - enum 本质是一个继承 java.lang.Enum 的 final 类，每个常量都是其实例
 *  - 枚举可带字段、构造方法、方法，比单纯常量更强大且类型安全
 *  - 自带 values()（所有常量数组）、valueOf()（按名取常量）等方法
 *  - enum 可 implements 接口；可用于 switch；可用 EnumSet / EnumMap
 */
public class Demo07 {

    public static void main(String[] args) {
        demoBasic();
        demoWithField();
        demoSwitch();
        demoInterface();
        demoEnumSetMap();
    }

    /** 1. 基础枚举 */
    private static void demoBasic() {
        System.out.println("=== 基础枚举 ===");
        for (Weekday d : Weekday.values()) {        // 遍历所有常量
            System.out.println("  " + d + " (ordinal=" + d.ordinal() + ")");
        }
        Weekday w = Weekday.valueOf("MON");         // 按名取常量
        System.out.println("valueOf(MON) : " + w);
        System.out.println("SUN == Weekday.SUN : " + (Weekday.SUN == Weekday.SUN)); // 同一实例
    }

    /** 2. 带字段与方法的枚举（更像类） */
    private static void demoWithField() {
        System.out.println("\n=== 带字段/方法的枚举 ===");
        for (Level lv : Level.values()) {
            System.out.println("  " + lv + " -> " + lv.getDesc() + "，优先级=" + lv.getPriority());
        }
        System.out.println("最高优先级 : " + Level.HIGH.getDesc());
    }

    /** 3. switch 中使用枚举 */
    private static void demoSwitch() {
        System.out.println("\n=== switch + 枚举 ===");
        printSeason(Season.SPRING);
        printSeason(Season.WINTER);
    }

    private static void printSeason(Season s) {
        switch (s) {
            case SPRING: System.out.println("  春天来了"); break;
            case SUMMER: System.out.println("  夏天炎热"); break;
            case AUTUMN: System.out.println("  秋天丰收"); break;
            case WINTER: System.out.println("  冬天寒冷"); break;
        }
    }

    /** 4. 枚举实现接口 */
    private static void demoInterface() {
        System.out.println("\n=== 枚举实现接口 ===");
        for (Operation op : Operation.values()) {
            System.out.println("  " + op + "(6,2) = " + op.apply(6, 2));
        }
    }

    /** 5. EnumSet 与 EnumMap */
    private static void demoEnumSetMap() {
        System.out.println("\n=== EnumSet / EnumMap ===");
        EnumSet<Weekday> weekends = EnumSet.of(Weekday.SAT, Weekday.SUN);
        System.out.println("周末 : " + weekends);

        EnumMap<Weekday, String> plan = new EnumMap<>(Weekday.class);
        plan.put(Weekday.MON, "开会");
        plan.put(Weekday.FRI, "复盘");
        System.out.println("周一计划 : " + plan.get(Weekday.MON));
    }
}

/** 简单枚举：星期 */
enum Weekday {
    MON, TUE, WED, THU, FRI, SAT, SUN
}

/** 带字段、构造方法、方法的枚举 */
enum Level {
    LOW("低", 1),
    MEDIUM("中", 2),
    HIGH("高", 3);

    private final String desc;
    private final int priority;

    /** 枚举构造方法必须是 private（可省略不写） */
    Level(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() { return desc; }
    public int getPriority() { return priority; }
}

/** 季节枚举（用于 switch） */
enum Season {
    SPRING, SUMMER, AUTUMN, WINTER
}

/** 枚举实现接口：把行为绑定到每个常量 */
interface Calculator {
    int apply(int a, int b);
}

enum Operation implements Calculator {
    ADD { public int apply(int a, int b) { return a + b; } },
    SUB { public int apply(int a, int b) { return a - b; } },
    MUL { public int apply(int a, int b) { return a * b; } },
    DIV { public int apply(int a, int b) { return b == 0 ? 0 : a / b; } };
}
