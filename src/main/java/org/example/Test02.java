package org.example;

public class Test02 {
    /*
      今天主要是学习，java的变量声明，以及常见的变量类型
      下面是类实例变量
     */
    String name = "李白"; // 声明一个字符串类型的变量 name，并赋值为 "李白"
    int age = 1000; // 声明一个整数类型的变量 age，并赋值为 1000
    String profession = "诗人"; // 声明一个字符串类型的变量 profession，并赋值为 "诗人"
    String hobby = "喝酒、写诗"; // 声明一个字符串类型的变量 hobby，并赋值为 "喝酒、写诗"

    // 实现主方法
    public static void main(String[] args) {
        Test02 test02 = new Test02(); // 创建 Test02 类的实例
        // 输出学习的主要内容
        System.out.println("java的变量声明，以及常见的变量类型");
        System.out.println("姓名：" + test02.name); // 输出姓名
        System.out.println("年龄：" + test02.age); // 输出年龄
        System.out.println("职业：" + test02.profession); // 输出职业
        System.out.println("爱好：" + test02.hobby); // 输出爱好

        final String constantValue = "这是一个常量"; // 声明一个常量，值不可改变
        System.out.println(constantValue); // 输出常量的值

        boolean myBool = true;
        System.out.println("这是一个布尔类型的变量 myBool，值为: " + myBool); // 声明一个布尔类型的变量 myBool，并赋值为 true

        char strName = "ABC".charAt(0); // 声明一个字符类型的变量 strName，并赋值为字符串 "ABC" 的第一个字符 'A'
        System.out.println("字符串 ABC 的第一个字符是: " + strName);
        /*
         * 直接输出计算结果
         * int: 整数类型，适用于没有小数部分的数字
         */
        int x = 10;
        int y = 20;
        int z = 30;
        System.out.println(x+y+z);

        // double: 双精度浮点数类型，适用于需要更高精度的计算
        double a = 1.5;
        double b = 1.25;
        System.out.println(a+b);

        // float: 单精度浮点数类型，适用于需要节省内存但不需要高精度的计算
        float c = 1.57f; // 注意：float 类型的字面值需要加上 'f' 后缀
        float d = 1.5f;
        System.out.println(c+d); // 3.0700002

    }



}
