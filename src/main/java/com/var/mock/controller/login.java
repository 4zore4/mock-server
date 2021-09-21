package com.var.mock.controller;

import com.var.mock.entity.User;
import com.var.mock.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.krb5.internal.PAData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class login {

    @Autowired
    private LoginService loginService;

    @ResponseBody
    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user){
        if (user == null || user.getUsername() ==null || user.getPassword() == null){
            return "不能为空";
        }
        Boolean isExist = loginService.isExist(user);
        if (isExist){
            Cookie cookie = new Cookie("user",user.getUsername());
            cookie.setPath("/");
            cookie.setMaxAge(60*1000);
            HttpSession session = request.getSession();
            session.setAttribute("user",user.getUsername());
            session.setMaxInactiveInterval(50*60);
            response.addCookie(cookie);
        }
        return "test";
    }

    @ResponseBody
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Boolean register(@RequestBody User user){
        if (user == null || user.getPassword() == null || user.getUsername() == null){
            return false;
        }
        return loginService.register(user);
    }
}
