package com.var.mock.entity;

import java.util.Map;


public class Data {
    private String path;
    private String code;
    private Map<String,String> json;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getJson() {
        return json;
    }

    public void setJson(Map<String, String> json) {
        this.json = json;
    }
}
