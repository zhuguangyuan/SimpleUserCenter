package com.bruce.algo.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 基本算法
 */
@Slf4j
@Component
public class SortBase {
    // 算法类型
    public enum SortType {
        EXCHANGE,
        SELECT,
        INSERT,
        QUICK_EXCHANGE1, // 快排
        QUICK_EXCHANGE2, // 快排
        QUICK_EXCHANGE3, // 快排
        HEAP_SELECT, // 堆选择
        SHELL_INSERT, // 希尔插入
    }

    protected boolean biggerThan(int[] arr, int a, int b) {
        return arr[a] > arr[b];
    }

    protected boolean bigOrEqualThan(int[] arr, int a, int b) {
        return arr[a] >= arr[b];
    }

    protected void swap(int[] arr, int a, int b) {
        if (a < 0 || b < 0 || a == b) {
            return;
        }
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    protected int[] copyOf(int[] arr, int begin, int end) {
        int[] copy = new int[end - begin + 1];
        System.arraycopy(arr, begin, copy, 0, end - begin + 1);
        return copy;
    }
}
