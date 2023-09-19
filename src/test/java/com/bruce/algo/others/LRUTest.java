package com.bruce.algo.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LRUTest {
    @InjectMocks
    private LRU lru;

    @Test
    void put() {
        lru.put(1, 1);
        lru.put(2, 2);

        // will let k-1 expire
        lru.put(3, 3);

        Assertions.assertEquals(-1, lru.get(1));
        Assertions.assertEquals(2, lru.get(2));
        Assertions.assertEquals(3, lru.get(3));

        Assertions.assertEquals(4, lru.put(2, 4));

        // will let key-3 expire
        Assertions.assertEquals(6, lru.put(1, 6));

        Assertions.assertEquals(4, lru.get(2));
        Assertions.assertEquals(-1, lru.get(3));
    }

    @Test
    void get() {
    }
}