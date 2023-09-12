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
        QUICK_EXCHANGE, // 快排
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

    protected void printArr(int[] arr) {
        if (arr == null) {
            return;
        }

        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
