package com.liu.gateway.test;

import org.springframework.beans.factory.annotation.Value;

public class Placeholder {
    @Value("$username:yinjihuan")
    private String username;
}
