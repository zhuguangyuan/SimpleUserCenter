package com.bruce.others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFilterTestItemUsageTest {

    @Test
    void testJsonSerializeFilter() throws JsonProcessingException {
        // serialize should ignore f2
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("myFilter", SimpleBeanPropertyFilter
                        .serializeAllExcept("f2")
                );
        JsonFilterUsageItem jsonFilterUsageItem = JsonFilterUsageItem.builder()
                .f1("f1")
                .f2("f2")
                .f3("f3")
                .build();
        String dtoAsString = new ObjectMapper().writer(filters).writeValueAsString(jsonFilterUsageItem);
        Assertions.assertTrue(dtoAsString.contains("f1"));
        Assertions.assertFalse(dtoAsString.contains("f2"));
        Assertions.assertTrue(dtoAsString.contains("f3"));

        // deserialize should contain f2
        FilterProvider filters2 = new SimpleFilterProvider()
                .addFilter("myFilter", SimpleBeanPropertyFilter
                        .serializeAllExcept()
                );
        JsonFilterUsageItem jsonFilterUsageItem1 = JsonFilterUsageItem.builder()
                .f1("f1")
                .f2("f2")
                .f3("f3")
                .build();
        String str = new ObjectMapper().writer(filters2).writeValueAsString(jsonFilterUsageItem1);
        JsonFilterUsageItem jsonFilterUsageItem2 = new ObjectMapper().readValue(str, JsonFilterUsageItem.class);
        Assertions.assertNotNull(jsonFilterUsageItem2.getF1());
        Assertions.assertNotNull(jsonFilterUsageItem2.getF2());
        Assertions.assertNotNull(jsonFilterUsageItem2.getF3());
    }

    @Test
    public void te1() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 4, 5,7,8,6));

        boolean hasConsecutiveSequence = numbers.stream()
                .anyMatch(n -> numbers.contains(n + 1) && numbers.contains(n + 2));

        System.out.println("Has consecutive sequence: " + hasConsecutiveSequence);
    }
}
