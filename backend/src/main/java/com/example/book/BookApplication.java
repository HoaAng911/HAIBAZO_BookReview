package com.example.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {
	
		Dotenv dotenv = Dotenv.configure()
				.directory("./backend")
				.ignoreIfMissing()
				.load();

		
		if (dotenv.entries().isEmpty()) {
			dotenv = Dotenv.configure().ignoreIfMissing().load();
		}

		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});

		java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		SpringApplication.run(BookApplication.class, args);
	}

}
