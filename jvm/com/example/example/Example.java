package com.example.example;

import com.example.schemas.v1.Person;

public class Example {
    public static Person makePerson() {
        return Person.builder().withName("Joe").build();
    }
}