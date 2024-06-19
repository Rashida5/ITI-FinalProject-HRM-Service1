package org.example.finalgradservice1;

import org.springframework.boot.SpringApplication;

public class TestFinalGradService1Application {

    public static void main(String[] args) {
        SpringApplication.from(FinalGradService1Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
