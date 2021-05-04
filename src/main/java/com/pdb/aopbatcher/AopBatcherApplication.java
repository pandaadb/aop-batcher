package com.pdb.aopbatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class AopBatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopBatcherApplication.class, args);
	}

}
