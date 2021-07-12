package org.example.springboot.controller;

import lombok.Data;

@Data
public class Employee extends User {
    private String company;
    private String deparment;
}
