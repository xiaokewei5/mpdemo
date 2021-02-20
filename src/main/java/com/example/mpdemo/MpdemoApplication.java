package com.example.mpdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MpdemoApplication {

	public static void main(String[] args) {
	    System.out.println("public static void main:" + (args.length>0?args[0]:args.length));
		SpringApplication.run(MpdemoApplication.class, args);
	}

}
