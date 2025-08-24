package com.snhu.sslserver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}
}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ChecksumController {
	
	@GetMapping("/hash")
	public String getChecksum() {
		String data = "Dylan Dunagan Checksum";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
			String base64Hash = Base64.getEncoder().encodeToString(hash);
			return "data: " + data + " | checksum: " + base64Hash;
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "Error computing checksum";
		}
	}
}