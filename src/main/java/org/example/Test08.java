package org.example;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * Java 日期时间（Date & Time）演示
 *
 * 推荐优先使用 java.time（Java 8+ 引入，线程安全、API 清晰）；
 * 旧的 Date / Calendar 已过时，仅作对比演示。
 */
public class Test08 {

    public static void main(String[] args) {
        demoLegacy();        // 旧 API（了解即可）
        demoNow();           // 获取当前时间
        demoCreate();        // 构造指定时间
        demoFormatParse();   // 格式化与解析
        demoPlusMinus();     // 加减计算
        demoCompare();       // 比较与间隔
        demoInstantZone();   // 时间戳与时区
        demoDurationPeriod();// 时间段
    }

    /** 1. 旧 API：Date / Calendar（了解，不推荐新代码使用） */
    private static void demoLegacy() {
        Date now = new Date();                       // 当前时间
        System.out.println("=== 旧 API（了解） ===");
        System.out.println("Date.toString : " + now);
        System.out.println("getTime(毫秒) : " + now.getTime());

        Calendar cal = Calendar.getInstance();
        cal.set(2026, Calendar.JANUARY, 1);          // 月份从 0 开始，易错！
        System.out.println("Calendar 指定 : " + cal.getTime());
        System.out.println("Calendar 加 1 天 : ");
        cal.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("  -> " + cal.getTime());
    }

    /** 2. 当前时间：LocalDate / LocalTime / LocalDateTime */
    private static void demoNow() {
        System.out.println("\n=== 当前时间 (java.time) ===");
        System.out.println("LocalDate.now : " + LocalDate.now());
        System.out.println("LocalTime.now : " + LocalTime.now());
        System.out.println("LocalDateTime.now : " + LocalDateTime.now());
    }

    /** 3. 构造指定时间 */
    private static void demoCreate() {
        LocalDate d = LocalDate.of(2026, 7, 11);          // 年 月 日
        LocalTime t = LocalTime.of(18, 30, 0);             // 时 分 秒
        LocalDateTime dt = LocalDateTime.of(d, t);
        System.out.println("\n=== 构造指定时间 ===");
        System.out.println("LocalDate.of : " + d);
        System.out.println("LocalTime.of : " + t);
        System.out.println("LocalDateTime.of : " + dt);
        System.out.println("年份 : " + d.getYear() + ", 月份 : " + d.getMonthValue() + ", 日 : " + d.getDayOfMonth());
        System.out.println("星期 : " + d.getDayOfWeek());
    }

    /** 4. 格式化与解析 */
    private static void demoFormatParse() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = now.format(fmt);                        // 日期 -> 字符串
        System.out.println("\n=== 格式化与解析 ===");
        System.out.println("format : " + s);

        String text = "2026-07-11 18:30:00";
        LocalDateTime parsed = LocalDateTime.parse(text, fmt); // 字符串 -> 日期
        System.out.println("parse : " + parsed);

        // 内置格式化器
        System.out.println("ISO_DATE : " + now.toLocalDate().format(DateTimeFormatter.ISO_DATE));
    }

    /** 5. 加减计算（不可变，返回新对象） */
    private static void demoPlusMinus() {
        LocalDate today = LocalDate.now();
        System.out.println("\n=== 加减计算 ===");
        System.out.println("today : " + today);
        System.out.println("加 3 天 : " + today.plusDays(3));
        System.out.println("减 1 月 : " + today.minusMonths(1));
        System.out.println("加 2 周 : " + today.plusWeeks(2));
        System.out.println("加 1 年 : " + today.plusYears(1));
        System.out.println("原对象不变 : " + today);
    }

    /** 6. 比较与间隔 */
    private static void demoCompare() {
        LocalDate a = LocalDate.of(2026, 1, 1);
        LocalDate b = LocalDate.of(2026, 7, 11);
        System.out.println("\n=== 比较与间隔 ===");
        System.out.println("a.isBefore(b) : " + a.isBefore(b));   // true
        System.out.println("a.isAfter(b) : " + a.isAfter(b));     // false
        System.out.println("相差天数 : " + ChronoUnit.DAYS.between(a, b));
        System.out.println("相差月数 : " + ChronoUnit.MONTHS.between(a, b));
    }

    /** 7. 时间戳 Instant 与时区 */
    private static void demoInstantZone() {
        Instant now = Instant.now();                       // UTC 时间戳
        System.out.println("\n=== 时间戳与时区 ===");
        System.out.println("Instant.now : " + now);
        System.out.println("毫秒 : " + now.toEpochMilli());

        ZonedDateTime bj = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime ny = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("北京 : " + bj);
        System.out.println("纽约 : " + ny);
    }

    /** 8. 时间段：Duration（时间）与 Period（日期） */
    private static void demoDurationPeriod() {
        LocalDateTime start = LocalDateTime.of(2026, 7, 11, 8, 0);
        LocalDateTime end = LocalDateTime.of(2026, 7, 11, 18, 30);
        Duration dur = Duration.between(start, end);       // 精确到时分秒
        System.out.println("\n=== 时间段 ===");
        System.out.println("Duration 小时 : " + dur.toHours() + " 分钟 : " + dur.toMinutes());

        LocalDate d1 = LocalDate.of(2026, 1, 1);
        LocalDate d2 = LocalDate.of(2026, 7, 11);
        Period period = Period.between(d1, d2);            // 年月日
        System.out.println("Period : " + period.getMonths() + " 月 " + period.getDays() + " 天");
    }
}
