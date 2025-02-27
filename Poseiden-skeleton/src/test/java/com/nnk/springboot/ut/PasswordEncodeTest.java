package com.nnk.springboot.ut;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Khang Nguyen. Email: khang.nguyen@banvien.com Date: 09/03/2019
 * Time: 11:26 AM
 */
@SpringBootTest
class PasswordEncodeTest {
	@Test
	void testPassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pw = encoder.encode("123456");
		System.out.println("[ " + pw + " ]");
		assertTrue(encoder.matches("123456", encoder.encode("123456")));
	}
}
