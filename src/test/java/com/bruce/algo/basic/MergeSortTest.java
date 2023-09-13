package com.bruce.algo.basic;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class MergeSortTest extends SortBaseTest {
    @InjectMocks
    private MergeSort mergeSort;

    @Override
    protected int[] test(int[] arr) {
        int[] result = mergeSort.sort(Arrays.copyOf(arr, arr.length), SortBase.SortType.MERGE);
        Assertions.assertNotNull(result);
        return result;
    }
}