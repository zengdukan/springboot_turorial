package com.example.tutorial;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("git")
public class GitConfig2 {
    
    private String name;

    private String email;

    private String ip;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "GitConfig [name=" + name + ", email=" + email + ", ip=" + ip + ", port=" + port + "]";
    }

}
