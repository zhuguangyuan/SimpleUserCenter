package com.bruce.algo.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 基本算法
 */
@Slf4j
@Component
public class BasicSort extends SortBase {
    public int[] basicSort(int[] arr, SortType sortType) {
        log.warn("basicSort, {} begin:{}", sortType, arr);
        int n = arr.length;
        // 进行n-1轮排序。每次选出第n-i大的，排在后面
        for (int i = 1; i < n; i++) {
            log.info("arr-{}-batch-{}", sortType, i);
            switch (sortType) {
                case EXCHANGE: {
                    // 冒泡，将最大的往后冒出
                    boolean flag = false;
                    for (int j = 0; j < n - i; j++) {
                        if (biggerThan(arr, j, j + 1)) {
                            log.info("arr-{}-batch-{}, item in {} bigger than {}, will exchange",
                                    sortType, i, j, j + 1);
                            swap(arr, j, j + 1);
                            flag = true;
                        }
                    }
                    if (!flag) {
                        log.error("inner loop has no exchange, sort completed.");
                        break;
                    }
                }
                break;
                case SELECT: {
                    // 选择，选出最大的和最后位置交换
                    int maxIndex = n - i;
                    for (int j = 0; j < n - i; j++) {
                        if (biggerThan(arr, j, maxIndex)) {
                            log.info("arr-{}-batch-{}, item in {} bigger than {}, will update maxIndex",
                                    sortType, i, j, maxIndex);
                            maxIndex = j;
                        }
                    }
                    swap(arr, maxIndex, n - i);
                }
                break;
                case INSERT: {
                    // 插入前面 i-1 长度的有序数组中
                    int key = arr[i];
                    int index = i - 1;
                    while (index >= 0 && arr[index] > key) {
                        log.info("arr-{}-batch-{}, item in {} is {}, bigger than {}, back forward.",
                                sortType, i, index, arr[index], key);
                        arr[index + 1] = arr[index];
                        index--;
                    }
                    arr[index + 1] = key;
                }
                break;
                default:
                    break;
            }
        }
        log.warn("basicSort, {} end:{}", sortType, arr);
        return arr;
    }
}
