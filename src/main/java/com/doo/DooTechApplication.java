package com.doo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by Sam Raju on 31/12/2021
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJms
public class DooTechApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DooTechApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DooTechApplication.class);
    }
}
