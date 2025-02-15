package com.nnk.springboot.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class OAuth2UserService.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

	/** The user service. */
	@Autowired
	private final UserService userService;

	/** The random. */
	private Random random = new Random();

	/**
	 * Load user.
	 *
	 * @param oAuth2UserRequest the o auth 2 user request
	 * @return the o auth 2 user
	 */
	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
		log.info("Load user {}", oAuth2UserRequest);
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

		String username = oAuth2User.getAttribute("login");

		User user = userService.getByUsername(username);

		if (user == null) {
			user = new User();
			user.setFullname(username);
			user.setUsername(username);
			user.setPassword(generateValidTemporaryPassword());
			user.setRole("ADMIN");
			userService.save(user);
		}
		Collection<GrantedAuthority> authorities = Collections
				.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

		return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "login");

	}

	/**
	 * Generate valid temporary password.
	 *
	 * @return the string
	 */
	private String generateValidTemporaryPassword() {
		String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String digits = "0123456789";
		String specialCharacters = "!@#$%^&*()-_=+<>?";

		StringBuilder password = new StringBuilder();

		password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
		password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
		password.append(digits.charAt(random.nextInt(digits.length())));
		password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

		String allCharacters = upperCaseLetters + lowerCaseLetters + digits + specialCharacters;
		while (password.length() < 12) {
			password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
		}

		Collections.shuffle(Arrays.asList(password.toString().split("")));

		return password.toString();
	}
}