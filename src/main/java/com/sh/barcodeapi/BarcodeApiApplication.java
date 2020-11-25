package com.sh.barcodeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class BarcodeApiApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(BarcodeApiApplication.class, args);
//	}
//
//}


@SpringBootApplication
public class BarcodeApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BarcodeApiApplication.class);
    }
}