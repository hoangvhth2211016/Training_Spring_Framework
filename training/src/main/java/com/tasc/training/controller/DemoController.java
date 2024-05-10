package com.tasc.training.controller;

import com.tasc.training.service.greet.GreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greet")
public class DemoController {

    @Autowired
    @Qualifier("greet_in_english")
    private GreetService greetEng;
    
    
    @Autowired
    @Qualifier("greet_in_vietnamese")
    private GreetService greetVie;
    
    @GetMapping("/hello")
    public String greetInEnglish(){
        return greetEng.hello();
    }
    
    @GetMapping("/xinChao")
    public String greetInVie(){
        return greetVie.hello();
    }
    
}
