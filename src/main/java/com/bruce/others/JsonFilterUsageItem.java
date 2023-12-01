package com.bruce.others;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonFilter("myFilter")
public class JsonFilterUsageItem {
    private String f1;
    private String f2;
    private String f3;
}
