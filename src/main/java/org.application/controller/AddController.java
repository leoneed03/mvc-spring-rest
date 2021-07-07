package org.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {

    @GetMapping("/add")
    public String say() {
        return "smth";
    }
}
