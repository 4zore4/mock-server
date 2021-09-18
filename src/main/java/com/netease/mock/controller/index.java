package com.netease.mock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class index {

    @ResponseBody
    @RequestMapping("/")
    public String index(){
        return "hello";
    }

}
