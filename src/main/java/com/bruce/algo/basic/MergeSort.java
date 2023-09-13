package com.bruce.algo.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MergeSort extends SortBase {
    @Override
    int[] sort(int[] arr, SortType sortType) {
        int[] tempArr = new int[arr.length];
        return mergeSort(arr, 0, arr.length - 1, tempArr, sortType, 0);
    }

    public int[] mergeSort(int[] arr, int left, int right, int[] tempArr, SortType sortType, int depth) {
        log.warn("{}-depth:{}", sortType, depth);

        if (arr == null || left >= right) {
            return arr;
        }
        int mid = (left + right) / 2;

        log.warn("{}-depth:{}, arr:{}, mid:{}", sortType, depth, arr, mid);
        mergeSort(arr, left, mid, tempArr, sortType, depth + 1);
        mergeSort(arr, mid + 1, right, tempArr, sortType, depth + 1);
        merge(arr, left, mid, right, tempArr, depth);
        return arr;
    }

    /**
     * 归并函数,将有序的arr[left,mid] arr[mid+1,right]归并成一个有序的arr
     * tempArr[]是用于归并的临时数组,作为参数传入主要是为了避免递归调用的时候频繁申请销毁数组
     * 主要思想是:两个指针i,j分别指向两个有序子数组,比较arr[i] arr[j] 较小的就先复制到tempArr[t]
     */
    private void merge(int[] arr, int left, int mid, int right, int[] tempArr, int depth) {
        log.warn("merge begin. depth:{}, arr:{}", depth, arr);

        int i = left;
        int j = mid + 1;
        int t = 0;

        // 两个指针分别指向两个有序数组，将较小的复制到tmpArr中
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tempArr[t++] = arr[i++];
            } else {
                tempArr[t++] = arr[j++];
            }
        }

        // 剩余的数据复制
        while (i <= mid) {
            tempArr[t++] = arr[i++];
        }
        while (j <= right) {
            tempArr[t++] = arr[j++];
        }

        // 复制到 arr
        t = 0;
        while (left <= right) {
            arr[left++] = tempArr[t++];
        }
        log.warn("merge end. depth:{}, arr:{}", depth, arr);
    }
}
