package com.var.mock.service;

import com.var.mock.component.RedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockService {

    @Autowired
    private RedisComponent redisComponent;


//    将路径和对应的json存储到redis里面
    public Boolean saveToRedis(String path, String code, String json){

//        String[] strArray = separatePath(path);
//        String uri = strArray[0];
//        String code = strArray[1];

        try {
            return redisComponent.hset(path, code, json);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


//    判断是否有相应的路径
    public Boolean isExist(String path){

//        to do 判断redis里面是否有这个路径

        return false;
    }

//    通过路径获取相对应的json
    public String getJsonByPath(String path){
        String uri = null;
        String code = null;
        try {
            String[] strArray = separatePath(path);
            if (strArray.length>0){
                uri = strArray[0];
                code = strArray[1];
                return redisComponent.hget(uri,code);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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

    private String[] separatePath(String path){
        String[] strArray = path.split("&");
        return strArray;
    }

}
