package com.capstone.teamProj_10.apiTest.user;

import lombok.Getter;

@Getter
public enum UserRole {
	ADMIN("ROLE_ADMIN"), 
	USER("ROLE_USER");

	UserRole(String value) {
		this.value = value;
	}

	private String value;
}
