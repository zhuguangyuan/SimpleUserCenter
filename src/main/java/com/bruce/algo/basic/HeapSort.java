package com.bruce.algo.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeapSort extends SortBase {
    @Override
    int[] sort(int[] arr, SortType sortType) {
        return heapSort(arr, sortType);
    }

    public int[] heapSort(int[] arr, SortType sortType) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        int len = arr.length;
        // 从中间节点开始建堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            adjust(arr, i, len);
        }
        // 交换堆顶节点，重新修正堆
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjust(arr, 0, i);
        }

        return arr;
    }

    // 保证以下标 targetPoint 为顶点的堆是大顶堆
    private void adjust(int[] arr, int targetPoint, int len) {
        for (int i = 2 * targetPoint + 1; i < len; i = 2 * i + 1) {
            if (i + 1 < len && arr[i] < arr[i + 1]) {
                i++;
            }
            if (arr[targetPoint] < arr[i]) {
                swap(arr, targetPoint, i);
            }
            targetPoint = i;
        }
    }
}
