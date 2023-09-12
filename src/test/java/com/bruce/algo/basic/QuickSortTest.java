package com.bruce.algo.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class QuickSortTest {
    @InjectMocks
    private QuickSort quickSort;

    @Test
    void quickSort() {
        int[] arr = {1, 2, 3, 4, 5, 3, 5, 6, 2, 1};
        int[] result = {1, 1, 2, 2, 3, 3, 4, 5, 5, 6};

        Assertions.assertArrayEquals(result,
                quickSort.quickSort(Arrays.copyOf(arr, arr.length), 0, arr.length - 1, 0, SortBase.SortType.QUICK_EXCHANGE));
    }
}