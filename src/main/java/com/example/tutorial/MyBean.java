package com.example.tutorial;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    public MyBean(ApplicationArguments args) {
        boolean debug = args.containsOption("output");
        List<String> files = args.getNonOptionArgs();
        if (debug) {
            System.out.println(args.getSourceArgs());
            System.out.println(args.getOptionNames());
            System.out.println(files);
            System.out.println(args.getOptionValues("output"));
        }
    }

}
