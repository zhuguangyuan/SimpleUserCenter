package com.bruce.algo.basic;

import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class HeapSortTest extends SortBaseTest {
    @InjectMocks
    private HeapSort heapSort;

    @Override
    protected int[] test(int[] arr) {

        return heapSort.sort(Arrays.copyOf(arr, arr.length), SortBase.SortType.HEAP_SELECT);
    }
}