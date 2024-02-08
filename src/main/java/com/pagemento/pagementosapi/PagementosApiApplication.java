package com.pagemento.pagementosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PagementosApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PagementosApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PagementosApiApplication.class);
    }
}
