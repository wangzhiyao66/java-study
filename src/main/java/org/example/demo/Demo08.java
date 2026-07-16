package org.example.demo;

import org.example.demo.util.StringUtil;   // 导入子包中的类

/**
 * Java 包（package）演示
 *
 * 要点：
 *  - 包用于按目录组织类，避免命名冲突，控制访问权限
 *  - 每个 .java 文件首行用 package 声明所属包，目录结构须与包名一致
 *  - 使用 import 引入其他包的类；同包内的类无需 import
 *  - 也可用「全限定名」直接引用（org.example.demo.util.StringUtil.xxx）
 *  - 访问规则：public 跨包可见；默认(包私有)仅同包可见
 */
public class Demo08 {

    public static void main(String[] args) {
        demoImport();
        demoFullyQualified();
        demoPackagePrivate();
        demoSamePackage();
    }

    /** 1. import 后使用其他包的类 */
    private static void demoImport() {
        System.out.println("=== import 使用其他包 ===");
        System.out.println("reverse : " + StringUtil.reverse("hello"));   // 来自 util 包
        System.out.println("isEmpty(null) : " + StringUtil.isEmpty(null));
        System.out.println("isEmpty(\"  \") : " + StringUtil.isEmpty("  "));
    }

    /** 2. 不使用 import，直接用全限定名（Fully Qualified Name） */
    private static void demoFullyQualified() {
        System.out.println("\n=== 全限定名引用 ===");
        String r = org.example.demo.util.StringUtil.reverse("package");
        System.out.println("全限定名调用 : " + r);
    }

    /** 3. 包私有成员：跨包无法访问（编译报错，演示说明） */
    private static void demoPackagePrivate() {
        System.out.println("\n=== 包私有访问说明 ===");
        System.out.println("StringUtil.internalTrim() 是包私有(default)方法，");
        System.out.println("org.example.demo 包无法直接调用，仅 util 包内可见。");
        // StringUtil.internalTrim("x");  // 取消注释会编译报错：not visible
    }

    /** 4. 同包类无需 import */
    private static void demoSamePackage() {
        System.out.println("\n=== 同包类无需 import ===");
        // Demo07、Demo01 等同在 org.example.demo，可直接用类名
        LevelSample.show();
    }
}

/** 同包辅助类（包 org.example.demo 内，无需 import 即可被 Demo08 使用） */
class LevelSample {
    static void show() {
        System.out.println("同包内直接引用，无需 import：LevelSample.show()");
    }
}
