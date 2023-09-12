package com.bruce.algo.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuickSort extends SortBase {
    public int[] quickSort(int[] arr, int left, int right, int depth, SortType sortType) {
        log.warn("{}-depth:{}", sortType, depth);
        if (left >= right || depth >= 50) {
            return arr;
        }
//        int[] pivot = partition1(arr, left, right, depth);
//        int[] pivot = partition2(arr, left, right, depth);
        int[] pivot = partition3(arr, left, right, depth);
        log.warn("{}-depth:{}, pivot:{}", sortType, depth, pivot);
        quickSort(arr, left, pivot[0] - 1, depth + 1, sortType);
        quickSort(arr, pivot[1] + 1, right, depth + 1, sortType);
        return arr;
    }

    // 一路，< 换左边
    private int[] partition1(int[] arr, int left, int right, int depth) {
        log.warn("进入 partition1-depth:{}", depth);

        printArr(arr);

        int key = arr[left];
        int index = left;

        swap(arr, left, right);
        while (index < right) {
            if (arr[index] < key) {
                swap(arr, left, index);
                left++;
            }
            index++;
        }
        swap(arr, left, right);

        printArr(arr);

        return new int[]{left, left};
    }

    // 二路，<= 换左边，>= 换右边. 注意这里的等于号，没有的话会有问题
    private int[] partition2(int[] arr, int left, int right, int depth) {
        log.warn("进入 partition2-depth:{}", depth);

        printArr(arr);

        int key = arr[left];
        int index = left;

        while (left < right) {
            while (left < right && arr[right] >= key) right--;
            while (left < right && arr[left] <= key) left++;
            swap(arr, left, right);
        }
        swap(arr, index, left);

        printArr(arr);

        return new int[]{left, right};
    }

    // 三路，< 换左边，= 放中间，> 换右边
    private int[] partition3(int[] arr, int left, int right, int depth) {
        log.warn("进入 partition3-depth:{}", depth);

        printArr(arr);

        int key = arr[left];
        int index = left;

        while (index <= right) {
            if (arr[index] == key) {
                index++;
            } else if (arr[index] > key) {
                swap(arr, index, right);
                right--;
            } else {
                swap(arr, left, index);
                left++;
            }
        }

        printArr(arr);

        return new int[]{left, right};
    }
}
