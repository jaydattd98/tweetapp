package com.tweetapp;

import com.tweetapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan("com.tweetapp.*")
@RequiredArgsConstructor
public class TweetAppApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(TweetAppApplication.class, args);
        System.out.println("Application is running Successfully..!!");

    }


    @Override
    public void run(String... args) {
//        userRepository.save(new UserEntity(1, "jaydatt", "Dhorsinge", "jaydattd98@gmail.com", "jaydattd98", "jaydatt@123", "9158224033", new ArrayList<>()));
    }
}
