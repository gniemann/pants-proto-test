package com.example.example;

import com.example.schemas.v1.PersonOuterClass;

public class Example {
    public static PersonOuterClass.Person makePerson() {
        return PersonOuterClass.Person.builder().withName("Joe").build();
    }
}