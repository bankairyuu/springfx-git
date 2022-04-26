package com.example.springfx.config;

import java.util.HashMap;
import java.util.Map;

public class GlobalConfig {
    private static final Map<ConfigKey, Object> config = new HashMap<>();

    public static <T> T get(ConfigKey key){
        return (T) config.get(key);
    }

    public static <T> void set(ConfigKey key, T value){
        config.put(key, value);
    }
}
