package com.bruce.algo.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShellSort extends SortBase {
    @Override
    public int[] sort(int[] arr, SortType sortType) {
        return shellSort(arr);
    }

    public int[] shellSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        int gap = arr.length;
        while (gap > 1) {
            gap = gap / 2;
            // 组下标应该是groupIndex+1，+2,+3...到+(gap-1)
            // 下列的注释掉的之前的写法也能实现排序，但是都转移到步长为1的步骤上了，导致速度大打折扣
//            for (int groupIndex = 0; groupIndex < arr.length; groupIndex += gap) {//bad
            for (int groupIndex = 0; groupIndex < gap; groupIndex++) {//good
                insert(arr, gap, groupIndex);
            }
        }

        return arr;
    }


    //对以下数组进行插排：arr[groupIndex, groupIndex+gap, groupIndex+2gap, groupIndex+3gap,...]
    private void insert(int[] arr, int gap, int groupIndex) {
//        System.out.println("n=" + arr.length + ", gap=" + gap + ", groupIndex = " + groupIndex);
        for (int i = groupIndex; i < arr.length; i += gap) {
            int key = arr[i];
            int tempIndex = i - gap;
            while (tempIndex >= groupIndex && arr[tempIndex] > key) {
                arr[tempIndex + gap] = arr[tempIndex];
                tempIndex -= gap;
            }
            arr[tempIndex + gap] = key;
        }
    }

}
