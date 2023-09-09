package com.bruce.auth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class Role {
    private String name;
}
