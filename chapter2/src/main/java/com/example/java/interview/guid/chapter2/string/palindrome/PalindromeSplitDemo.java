package com.example.java.interview.guid.chapter2.string.palindrome;

import java.util.List;

/**
 * @Author : zhouguanya
 * @Project : java-interview-guide
 * @Date : 2019/6/26 16:24
 * @Version : V1.0
 * @Description : 分割回文串测试
 */
public class PalindromeSplitDemo {
    public static void main(String[] args) {
        String content = "aab";
        PalindromeSplit palindromeSplit = new PalindromeSplit(content);
        // 分割回文
        List<List<String>> result = palindromeSplit.split();
        System.out.println(content + "分割回文串结果：");
        for (List<String> list : result) {
            System.out.println(list);
        }
    }
}
