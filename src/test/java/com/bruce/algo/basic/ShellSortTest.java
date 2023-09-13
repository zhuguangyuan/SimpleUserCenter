package com.bruce.algo.basic;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class ShellSortTest extends SortBaseTest {
    @InjectMocks
    private ShellSort shellSort;

    @Override
    protected int[] test(int[] arr) {
        int[] result = shellSort.sort(Arrays.copyOf(arr, arr.length), SortBase.SortType.SHELL_INSERT);
        Assertions.assertNotNull(result);
        return result;
    }
}