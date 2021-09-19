package com.var.mock.controller;

import com.alibaba.fastjson.JSON;
import com.var.mock.entity.Data;
import com.var.mock.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Controller
public class index {

    @Autowired
    private MockService mockService;

    @ResponseBody
    @RequestMapping("/mock/**")
    public String test(HttpServletRequest request, HttpServletResponse response){
        String requestPath = request.getServletPath();
        String res = mockService.getJsonByPath(requestPath);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/savePath", method = RequestMethod.POST)
    public String savePath(@RequestBody Data data){
        String path = data.getPath();
        String code = data.getCode();
        Map<String, String> json = data.getJson();
        System.out.println(path);
        System.out.println(code);
        System.out.println(json.toString());
        Boolean flag = mockService.saveToRedis(path, code, JSON.toJSONString(json));
        return flag.toString();
    }

}
