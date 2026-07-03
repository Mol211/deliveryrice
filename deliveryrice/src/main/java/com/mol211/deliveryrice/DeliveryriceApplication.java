package com.mol211.deliveryrice;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;

@SpringBootApplication
public class DeliveryriceApplication {

	public static void main(String[] args) {

		SpringApplication.run(DeliveryriceApplication.class, args);
	}

}
