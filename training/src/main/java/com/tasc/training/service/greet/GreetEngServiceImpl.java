package com.tasc.training.service.greet;

import org.springframework.stereotype.Service;

@Service("greet_in_english")
public class GreetEngServiceImpl implements GreetService{
    
    @Override
    public String hello() {
        return "hello world";
    }
}
