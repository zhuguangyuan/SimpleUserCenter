package com.bruce.algo.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class SortBaseTest {
    protected int[][] testArr = {
            // test case 0
            {},
            {},
            // test case 0
            {0},
            {0},
            // test case 1
            {1, 2, 3, 4, 5, 3, 5, 6, 2, 1},
            {1, 1, 2, 2, 3, 3, 4, 5, 5, 6},
            // test case 2
            {1, -1, 0, -1, -1},
            {-1, -1, -1, 0, 1},
            // test case 3
            {1, 2, 3, 4, 5, 3, 2, 0, -1, -2, 3},
            {-2, -1, 0, 1, 2, 2, 3, 3, 3, 4, 5},
            // test case 4
            {5, 2, 4, 5, 4, 2, 4, 6, 3, 1, 5, 3, 2, 4, 4, 3},
            {1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 6},
    };

    @Test
    void testSort() {
        for (int i = 0; i < testArr.length; i += 2) {
            int[] arr1 = testArr[i];
            int[] result = testArr[i + 1];
            Assertions.assertArrayEquals(result, test(arr1));
        }
    }

    protected int[] test(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }
}