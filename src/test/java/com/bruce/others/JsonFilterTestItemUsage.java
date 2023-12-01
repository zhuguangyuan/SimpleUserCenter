package com.bruce.others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonFilterTestItemUsage {

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
}
