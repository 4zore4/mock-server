package com.var.mock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class index {

    @ResponseBody
    @RequestMapping("/mock/**")
    public String test(HttpServletRequest request, HttpServletResponse response){
        String requestPath = request.getServletPath().replace("/mock","");
        return requestPath;
    }

}
