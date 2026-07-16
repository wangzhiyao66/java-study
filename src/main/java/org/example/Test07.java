package org.example;

import java.util.Arrays;
import java.util.Random;

/**
 * Java 数组（Array）演示
 *
 * 数组是固定长度的、同类型的连续内存容器。
 */
public class Test07 {

    public static void main(String[] args) {
        demoDeclareAndInit();
        demoTraverse();
        demoCommonUtils();
        demoCopy();
        demoSortAndSearch();
        demoMultiDim();
        demoArrayAsParam();
        demoVarargs();
    }

    /** 1. 声明与初始化（一维数组多种方式） */
    private static void demoDeclareAndInit() {
        int[] a1 = new int[3];              // 指定长度，默认 0
        int[] a2 = {1, 2, 3};              // 静态初始化
        int[] a3 = new int[]{4, 5, 6};     // 完整写法

        System.out.println("=== 声明与初始化 ===");
        System.out.println("长度 a1 : " + a1.length);
        System.out.println("a2 : " + Arrays.toString(a2));
        System.out.println("a3 : " + Arrays.toString(a3));

        // 引用类型数组
        String[] names = {"Alice", "Bob", "Carol"};
        System.out.println("字符串数组 : " + Arrays.toString(names));
    }

    /** 2. 遍历数组 */
    private static void demoTraverse() {
        int[] arr = {10, 20, 30};
        System.out.println("\n=== 遍历数组 ===");

        System.out.print("for 循环 : ");
        for (int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
        System.out.println();

        System.out.print("增强 for : ");
        for (int v : arr) System.out.print(v + " ");
        System.out.println();

        // Java 8+ forEach 配合 lambda
        System.out.print("forEach : ");
        Arrays.stream(arr).forEach(v -> System.out.print(v + " "));
        System.out.println();
    }

    /** 3. 常用工具：填充、比较、二分查找 */
    private static void demoCommonUtils() {
        int[] arr = new int[5];
        Arrays.fill(arr, 7);                       // 全部填充
        System.out.println("\n=== 常用工具 ===");
        System.out.println("fill : " + Arrays.toString(arr));

        int[] b1 = {1, 2, 3};
        int[] b2 = {1, 2, 3};
        System.out.println("equals : " + Arrays.equals(b1, b2));   // true

        int[] data = {5, 2, 9, 1, 7};
        int idx = Arrays.binarySearch(data, 9);    // 需先排序
        System.out.println("binarySearch(未排序) : " + idx);
    }

    /** 4. 数组拷贝 */
    private static void demoCopy() {
        int[] src = {1, 2, 3, 4, 5};
        int[] dst = Arrays.copyOf(src, src.length);          // 完整拷贝
        int[] part = Arrays.copyOfRange(src, 1, 4);          // 拷贝区间 [1,4)
        int[] manual = new int[3];
        System.arraycopy(src, 0, manual, 0, 3);             // 原生高效拷贝

        System.out.println("\n=== 数组拷贝 ===");
        System.out.println("copyOf : " + Arrays.toString(dst));
        System.out.println("copyOfRange : " + Arrays.toString(part));
        System.out.println("arraycopy : " + Arrays.toString(manual));
        System.out.println("地址是否相同(应为false) : " + (dst == src));
    }

    /** 5. 排序与查找 */
    private static void demoSortAndSearch() {
        int[] arr = {5, 2, 9, 1, 7, 3};
        Arrays.sort(arr);                                 // 升序排序
        System.out.println("\n=== 排序与查找 ===");
        System.out.println("sort : " + Arrays.toString(arr));
        int idx = Arrays.binarySearch(arr, 7);            // 排序后二分查找
        System.out.println("binarySearch(7) : " + idx);
    }

    /** 6. 多维数组（以二维为例） */
    private static void demoMultiDim() {
        // 规则二维数组
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        // 不规则二维数组
        int[][] jagged = new int[2][];
        jagged[0] = new int[2];
        jagged[1] = new int[3];

        System.out.println("\n=== 多维数组 ===");
        System.out.println("matrix 行数 : " + matrix.length + ", 列数 : " + matrix[0].length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("jagged 不规则 : " + Arrays.deepToString(jagged));
    }

    /** 7. 数组作为方法参数与返回值 */
    private static void demoArrayAsParam() {
        int[] arr = {3, 1, 2};
        System.out.println("\n=== 作为参数/返回值 ===");
        System.out.println("求和 : " + sum(arr));
        System.out.println("最大值 : " + max(arr));
        int[] doubled = scale(arr, 2);              // 返回新数组
        System.out.println("翻倍 : " + Arrays.toString(doubled));
    }

    private static int sum(int[] a) {
        int s = 0;
        for (int v : a) s += v;
        return s;
    }

    private static int max(int[] a) {
        int m = a[0];
        for (int v : a) if (v > m) m = v;
        return m;
    }

    private static int[] scale(int[] a, int factor) {
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i++) r[i] = a[i] * factor;
        return r;
    }

    /** 8. 可变参数（本质是数组） */
    private static void demoVarargs() {
        System.out.println("\n=== 可变参数 ===");
        System.out.println("varargs : " + sumAll(1, 2, 3, 4, 5));
        System.out.println("varargs(空) : " + sumAll());
        // 也可直接传数组
        int[] arr = {10, 20};
        System.out.println("传数组 : " + sumAll(arr));
    }

    private static int sumAll(int... nums) {
        int s = 0;
        for (int v : nums) s += v;
        return s;
    }
}
