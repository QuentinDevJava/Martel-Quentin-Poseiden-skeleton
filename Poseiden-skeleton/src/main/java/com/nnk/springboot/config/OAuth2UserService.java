package com.nnk.springboot.config;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

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
 * Service to load OAuth2 users. If the user does not exist, a new user is
 * created with a temporary password and a default "USER" role.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

	/** Service to access users. */
	private final UserService userService;

	/** The random. */
	private final Random random = new SecureRandom();

	/**
	 * Loads an OAuth2 user, creating a new user if needed.
	 * 
	 * @param oAuth2UserRequest the OAuth2 user request.
	 * @return the OAuth2 user with its roles and attributes.
	 */
	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {

		// Retrieve the OAuth2 client name GitHub, Google, etc.)
		String clientOAuth2Name = oAuth2UserRequest.getClientRegistration().getClientName();
		log.info("User loaded by {}", clientOAuth2Name);

		// Check if the OAuth2 client is GitHub
		if (clientOAuth2Name.contentEquals("GitHub")) {
			OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
			String username = oAuth2User.getAttribute("login");
			log.info("Username of user: {}", username);

			// Check if the username is blank or null, and throw an exception if it is
			if (username.isBlank() || username == null) {
				log.error("Login value is invalid: {}", username);
				throw new UnsupportedOperationException("OAuth2 client " + clientOAuth2Name + " is not supported.");
			} else {
				User user = userService.getByUsername(username);

				// If the user doesn't exist in the database, create one with a temporary
				// password and a default role USER
				if (user == null) {
					user = new User(username, username, generateValidTemporaryPassword(), "USER");
					userService.save(user);
					log.info("New user created and added to DB: username = {}, role = {}", username, user.getRole());
				}
				Collection<GrantedAuthority> authorities = Collections
						.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
				log.info("User found in DB: username = {}, role = {}", username, user.getRole());
				return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "login");
			}
		} else {
			// If the OAuth2 client is not supported, throw an exception
			log.error("Unsupported OAuth2 client: {}. The client is not supported.", clientOAuth2Name);
			throw new UnsupportedOperationException("OAuth2 client " + clientOAuth2Name + " is not supported.");
		}
	}

	/**
	 * Generate valid temporary password.
	 * 
	 * @return a temporary password.
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