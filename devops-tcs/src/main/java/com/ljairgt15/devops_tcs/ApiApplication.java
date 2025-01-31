package com.ljairgt15.devops_tcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Base64;


@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		/*Secret in hexadecimal*/
		String secret="09c53c457e150766aacce02ca27ec8929797b4a3b1093d5bc6aabc0f4b4c307d";
		System.out.println("clave"+ Base64.getEncoder().encodeToString(secret.getBytes()));
		SpringApplication.run(ApiApplication.class, args);
	}

}
