package com.jerry.distributed.rpc.utils;

import lombok.Data;
import lombok.Getter;

import java.io.IOException;
import java.util.Properties;

/**
 * description: PropertiesUtils <br>
 * date: 2021/1/4 16:26 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/416:26
 * @version: 1.0 <br>
 */
public class PropertiesUtils {
    private static Properties properties;

    static {
        try{
            properties = new Properties();
            properties.load(PropertiesUtils.class.getResourceAsStream("/app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String key){
        return (String) properties.get("key");
    }
}
