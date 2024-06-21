package org.example.finalgradservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.example.finalgradservice1.repository")
@EntityScan("org.example.finalgradservice1.model")
public class FinalGradService1Application {

    public static void main(String[] args) {

        SpringApplication.run(FinalGradService1Application.class, args);
    }

}
