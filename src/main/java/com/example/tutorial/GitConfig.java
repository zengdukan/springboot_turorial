package com.example.tutorial;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitConfig {
    
    @Value("${git.name}")
    private String name;

    @Value("${git.email}")
    private String email;

    @Value("${git.ip}")
    private String ip;

    @Value("${git.port}")
    private int port;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "GitConfig [name=" + name + ", email=" + email + ", ip=" + ip + ", port=" + port + "]";
    }

}
