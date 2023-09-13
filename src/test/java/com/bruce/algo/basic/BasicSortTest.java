package com.bruce.algo.basic;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class BasicSortTest extends SortBaseTest {
    @InjectMocks
    private BasicSort basicSort;

    @Override
    protected int[] test(int[] arr) {
        int[] result1 = basicSort.sort(Arrays.copyOf(arr, arr.length), BasicSort.SortType.EXCHANGE);
        int[] result2 = basicSort.sort(Arrays.copyOf(arr, arr.length), BasicSort.SortType.SELECT);
        int[] result3 = basicSort.sort(Arrays.copyOf(arr, arr.length), BasicSort.SortType.INSERT);

        Assertions.assertArrayEquals(result1, result2);
        Assertions.assertArrayEquals(result1, result3);
        return result1;
    }
}