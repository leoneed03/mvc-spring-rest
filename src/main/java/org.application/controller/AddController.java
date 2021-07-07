package org.application.controller;

import org.application.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {

    @GetMapping("/add")
    public String say() {
        return "smth";
    }

    @GetMapping("/j")
    @ResponseBody
    public Person getJson() {
        return new Person();
    }
}
