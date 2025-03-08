package com.oshiel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OshielApplication {

    public static void main(String[] args) {
        SpringApplication.run(OshielApplication.class, args);
    }

}
