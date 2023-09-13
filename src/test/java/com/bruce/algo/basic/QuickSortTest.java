package com.bruce.algo.basic;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class QuickSortTest extends SortBaseTest {
    @InjectMocks
    private QuickSort quickSort;

    @Override
    protected int[] test(int[] arr) {
        int[] result1 = quickSort.sort(Arrays.copyOf(arr, arr.length), SortBase.SortType.QUICK_EXCHANGE1);
        int[] result2 = quickSort.sort(Arrays.copyOf(arr, arr.length), SortBase.SortType.QUICK_EXCHANGE2);
        int[] result3 = quickSort.sort(Arrays.copyOf(arr, arr.length), SortBase.SortType.QUICK_EXCHANGE3);
        Assertions.assertArrayEquals(result1, result2);
        Assertions.assertArrayEquals(result1, result3);
        return result1;
    }
}