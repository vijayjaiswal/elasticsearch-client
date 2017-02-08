package com.vijay.web.insight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vijay.web.core.listener")
@ComponentScan("com.vijay.web.insight")
public class InsightApp {

    public static void main(String[] args) {
        SpringApplication.run(InsightApp.class, args);
    }
}
