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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.websocket.Session;
import java.util.*;

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
    public Map<String,String> savePath(@RequestBody Data data,HttpServletRequest request){
        Map<String,String> response = new HashMap<>();
        boolean isLogin = isLogin(request);
        if (!isLogin){
            response.put("errmsg","请先登录");
            return response;

        }
        String path = data.getPath();
        String code = data.getCode();
        Map<String, String> json = data.getJson();
        Boolean flag = mockService.saveToRedis(path, code, JSON.toJSONString(json));
        response.put("success",flag.toString());
        return response;
    }

    @ResponseBody
    @RequestMapping(path = "/test",method = RequestMethod.GET)
    public String test1(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = new Cookie("user","qiao");
        cookie.setPath("/test");
        cookie.setMaxAge(100*60);
        cookie.setValue("test");
        response.addCookie(cookie);
        return "test";
    }

//    判断是否已经登录
    private Boolean isLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        List<String> cookieValues = new ArrayList();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")){
                cookieValues.add(cookie.getValue());
            }
        }
        if (cookieValues.isEmpty()){
            return false;
        }

        HttpSession session = request.getSession();
        String sessionValue = (String) session.getAttribute("user");
        if (sessionValue == null || sessionValue.length() == 0 || !cookieValues.contains(sessionValue)){
            return false;
        }
        return true;
    }

}
