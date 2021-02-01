package ru.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEurekaServerApplication.class, args);
    }

}
