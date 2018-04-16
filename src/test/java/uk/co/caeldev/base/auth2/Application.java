package uk.co.caeldev.base.auth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.caeldev.base.auth2.config.EnableBaseAuthServer;

@SpringBootApplication
@EnableBaseAuthServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

