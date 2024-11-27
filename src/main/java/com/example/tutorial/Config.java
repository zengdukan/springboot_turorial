package com.example.tutorial;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${outfile}")
    private String outFile;

    public String getOutFile() {
        return outFile;
    }
}
