package com.mari.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Long userId;
	private String username;
	private String password;
	private String email;
	private String address1;
	private String address2;
	private String phone;
	private Role role;

}
