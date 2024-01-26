package spring.in.action.chap11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "spring.in.action.chap11")
public class Chap11Application {
    public static void main(String[] args) {
        SpringApplication.run(Chap11Application.class, args);
    }
}
