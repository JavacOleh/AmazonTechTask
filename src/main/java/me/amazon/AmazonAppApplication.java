package me.amazon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "me.amazon")
@EnableCaching
public class AmazonAppApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AmazonAppApplication.class, args);
	}
}
