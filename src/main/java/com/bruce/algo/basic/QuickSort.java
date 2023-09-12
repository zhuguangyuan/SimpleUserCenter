package com.bruce.algo.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuickSort extends SortBase {
    /**
     * 选择不同的分区函数进行排序
     *
     * @param arr      待排序数组
     * @param left     左下标
     * @param right    右下标
     * @param depth    递归深度
     * @param sortType 分区类型
     * @return
     */
    public int[] quickSort(int[] arr, int left, int right, int depth, SortType sortType) {
        log.warn("{}-depth:{}", sortType, depth);
        if (left >= right) {
            return arr;
        }
        int[] pivot = new int[2];
        switch (sortType) {
            case QUICK_EXCHANGE1:
                pivot = partition1(arr, left, right, depth);
                break;
            case QUICK_EXCHANGE2:
                pivot = partition2(arr, left, right, depth);
                break;
            case QUICK_EXCHANGE3:
                pivot = partition3(arr, left, right, depth);
                break;
            default:
                break;
        }
        log.warn("{}-depth:{}, arr:{}, pivot:{}", sortType, depth, arr, pivot);
        quickSort(arr, left, pivot[0] - 1, depth + 1, sortType);
        quickSort(arr, pivot[1] + 1, right, depth + 1, sortType);
        return arr;
    }

    // 一路，< 换左边
    private int[] partition1(int[] arr, int left, int right, int depth) {
        int originLeft = left;
        int originRight = right;
        log.warn("partition1 begin. depth:{}, arr:{}", depth, copyOf(arr, originLeft, originRight));

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

        log.warn("partition1 end. depth:{}, arr:{}", depth, copyOf(arr, originLeft, originRight));

        // 注意right右边的不一定是大于key的，也可能等于key
        // 所以这里不能返回{left, right}
        return new int[]{left, left};
    }

    // 二路，< 换左边，> 换右边. 注意这里没用等号
    private int[] partition2(int[] arr, int left, int right, int depth) {
        int originLeft = left;
        int originRight = right;
        log.warn("partition2 begin. depth:{}, arr:{}", depth, copyOf(arr, originLeft, originRight));

        int key = arr[left];
        int index = left;

        while (left < right) {
            // 注意只找小于key的right, 大于key的left, 然后交换
            while (left < right && arr[right] >= key) right--;
            while (left < right && arr[left] <= key) left++;
            swap(arr, left, right);
        }
        swap(arr, index, left);

        log.warn("partition2 end. depth:{}, arr:{}", depth, copyOf(arr, originLeft, originRight));

        // 两路分区后，左边的都是小于key的，右边的都是大于key的，所以这里返回{left, right}
        return new int[]{left, right};
    }

    // 三路，< 换左边，= 放中间，> 换右边
    private int[] partition3(int[] arr, int left, int right, int depth) {
        int originLeft = left;
        int originRight = right;
        log.warn("partition3 begin. depth:{}, arr:{}", depth, copyOf(arr, originLeft, originRight));

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
        log.warn("partition3 end. depth:{}, arr:{}", depth, copyOf(arr, originLeft, originRight));

        return new int[]{left, right};
    }
}
