package com.tasc.training.service.greet;

import org.springframework.stereotype.Service;

@Service("greet_in_vietnamese")
public class GreetVieServiceImpl implements GreetService{
    @Override
    public String hello() {
        return "xin chao";
    }
}
