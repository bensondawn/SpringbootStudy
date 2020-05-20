package com.benson.elasticsearch.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Employee {
    private String id;
    private String name;
    String sex;
    int age;
    int score;
    String role;
}
