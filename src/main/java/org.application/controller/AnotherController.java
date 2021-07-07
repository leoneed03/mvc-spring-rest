package org.application.controller;

import org.application.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnotherController {
    @GetMapping("/home")
    public Person getPerson() {
        return new Person("John", 20);
    }
}
