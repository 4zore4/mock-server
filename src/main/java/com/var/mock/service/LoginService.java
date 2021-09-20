package com.var.mock.service;

import com.var.mock.component.RedisComponent;
import com.var.mock.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private RedisComponent redisComponent;

    public Boolean isExist(User user){
        String pwd = redisComponent.get(user.getUsername());
        if (pwd == null || pwd.length()==0){
            return false;
        }
        if (user.getPassword().equals(pwd)){
            return true;
        }
        return false;
    }

    public Boolean register(User user){
        if (user == null){
            return null;
        }
        return redisComponent.set(user.getUsername(),user.getPassword());
    }

}
