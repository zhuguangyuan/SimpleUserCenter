package com.bruce.algo.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class BasicSortTest {
    @InjectMocks
    private BasicSort basicSort;

    @Test
    void basicSort() {
        int[] arr = {1, 2, 3, 4, 5, 3, 5, 6, 2, 1};
        int[] result = {1, 1, 2, 2, 3, 3, 4, 5, 5, 6};
        Assertions.assertArrayEquals(result,
                basicSort.basicSort(Arrays.copyOf(arr, arr.length), BasicSort.SortType.EXCHANGE));
        Assertions.assertArrayEquals(result,
                basicSort.basicSort(Arrays.copyOf(arr, arr.length), BasicSort.SortType.SELECT));
        Assertions.assertArrayEquals(result,
                basicSort.basicSort(Arrays.copyOf(arr, arr.length), BasicSort.SortType.INSERT));
    }
}