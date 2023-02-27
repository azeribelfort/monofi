package com.monofi;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class MonofiApplication {

    public static void main(String[] args){
        SpringApplication.run(MonofiApplication.class);
    }
}
