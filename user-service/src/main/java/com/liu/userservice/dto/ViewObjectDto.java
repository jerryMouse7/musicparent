package com.liu.userservice.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class ViewObjectDto {
    private Map<String, Object> map = new HashMap<>();

    public void set(String key,Object value){
        map.put(key, value);
    }
    public Object get(String key){
        return map.get(key);
    }
}
