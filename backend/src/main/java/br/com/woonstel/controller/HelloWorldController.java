package br.com.woonstel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing")
public class HelloWorldController {
    @GetMapping
    public String HelloWord(){
        return "Hello World!!!!";
    }

}
