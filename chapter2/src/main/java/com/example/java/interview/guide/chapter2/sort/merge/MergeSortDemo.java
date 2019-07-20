package com.example.java.interview.guide.chapter2.sort.merge;

/**
 * @Author : zhouguanya
 * @Project : java-interview-guide
 * @Date : 2019/7/20
 * @Version : V1.0
 * @Description : 测试归并排序
 */
public class MergeSortDemo {
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] array = {42, 20, 17, 13, 28, 14, 23, 15};
        int[] result = mergeSort.sort(array);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
