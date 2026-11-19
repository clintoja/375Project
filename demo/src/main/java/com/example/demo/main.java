package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jackson.autoconfigure.*;


@SpringBootApplication 
public class main {
    


    public static void main (String[] args){


        site_context sc = new site_context();
        SpringApplication.run(main.class, args);

    }
}
