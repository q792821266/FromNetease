package com.study.hystrix.controller;

import com.study.hystrix.command.CustomerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index")
    public Object getIndex(){
        return new CustomerCommand(restTemplate).execute();
    }
}
