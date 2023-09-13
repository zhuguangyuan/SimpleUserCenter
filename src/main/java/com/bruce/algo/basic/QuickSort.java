package com.bruce.algo.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class QuickSort extends SortBase {
    @Override
    int[] sort(int[] arr, SortType sortType) {
        return quickSort(arr, 0, arr.length - 1, sortType, 0);
    }

    public int[] quickSort(int[] arr, int left, int right, SortType sortType, int depth) {
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
        quickSort(arr, left, pivot[0] - 1, sortType, depth + 1);
        quickSort(arr, pivot[1] + 1, right, sortType, depth + 1);
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


    /**
     * 双路快排的一些优化
     */
    // 选择一个枢纽，并将小于等于枢纽元素的元素放在左边，大于等于枢纽元素的元素放在右边
    // 同时返回枢纽元素的左边界和右边界
    // 注释掉的部分是 对于数组中重复元素较多的情况下的优化
    private int[] partition(int[] arr, int left, int right) {
//        int leftIndex = left;
//        int rightIndex = right;
//        int oldLeft = left;
//        int oldRight = right;

        int key = getKey(arr, left, right);
        int pivot = left;

        // 先从右到左选出小于枢纽元素的值，用于和枢纽元素做交换
        // 再从左到右，选出比枢纽元素大的值，和枢纽元素做交换
        // 为了减少交换次数，可以以上两步中只交换选中的两个元素
        // 退出循环的时候再交换枢纽元素和中间值
        while (left < right) {
            while (left < right && arr[right] >= key) {
                right--;
            }
            while (left < right && arr[left] <= key) {
                left++;
            }
            swap(arr, left, right);
        }
        while (left < right) {
            while (left < right && arr[right] >= key) {
//                if (arr[right] == key) {
//                    swap(arr,right,rightIndex);
//                    rightIndex--;
//                }
                right--;
            }
            while (left < right && arr[left] <= key) {
//                if (arr[left] == key) {
//                    swap(arr,leftIndex,left);
//                    leftIndex++;
//                }
                left++;
            }
            swap(arr, left, right);
        }
        swap(arr, pivot, left);

//        while(--leftIndex >= oldLeft && arr[--left] != key) {
//            swap(arr,leftIndex,left);
//        }
//        while(++rightIndex <= oldRight && arr[++right] != key) {
//            swap(arr,right,rightIndex);
//        }

        return new int[]{left, right};
    }

    // 选取随机枢纽元素, 同时将它调到第一个位置
    private int getKey(int[] arr, int left, int right) {
        int randomIndex = new Random().nextInt(right - left) + left;
        swap(arr, left, randomIndex);
        return arr[left];
    }
}
