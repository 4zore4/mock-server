package com.var.mock.service;

import org.springframework.stereotype.Service;

@Service
public class mockService {

//    将路径和对应的json存储到redis里面
    public Integer saveToRedis(String path, String json){

//        to do save to redis
        return 0;
    }


//    判断是否有相应的路径
    public Boolean isExist(String path){

//        to do 判断redis里面是否有这个路径

        return false;
    }

//    通过路径获取相对应的json
    public String getJsonByPath(String path){

//        to do

        return null;
    }

//    提供一个方法删除path
    public Boolean del(String path){
//        to do

        return false;
    }

//    提供一个方法修改path
    public Boolean reset(String path){
//        to do
        return false;
    }

}
