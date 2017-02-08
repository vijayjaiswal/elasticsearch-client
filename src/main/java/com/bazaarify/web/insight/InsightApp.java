package com.bazaarify.web.insight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bazaarify.web.core.listener")
@ComponentScan("com.bazaarify.web.insight")
public class InsightApp {

    public static void main(String[] args) {
        SpringApplication.run(InsightApp.class, args);
    }
}
