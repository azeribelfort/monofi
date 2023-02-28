package com.monofi;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class MonofiApplication {

    @PostConstruct
    public void setup(){
        Stripe.apiKey = "sk_test_51Mg9vLG3lJlknf5X1FVTsaKXuJmXF4jkVcCRFDt5P5bu77DjUHt3foVOFBIY09ym4dcqdHiy0GxaYLqiY9xv9bZI009FApjNLo";
    }
    public static void main(String[] args){
        SpringApplication.run(MonofiApplication.class);
    }
}
