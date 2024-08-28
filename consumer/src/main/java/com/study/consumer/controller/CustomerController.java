package com.study.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;

    public Object getIndex(){
        return restTemplate.getForObject("http://HelloServer/index", String.class, "");
    }
}
