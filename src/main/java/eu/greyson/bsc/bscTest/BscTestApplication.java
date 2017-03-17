package eu.greyson.bsc.bscTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BscTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(BscTestApplication.class, args);
	}
}
