package com.trust.rms.JWT;

import lombok.Data;

@Data
public class AuthRequest {
	private String email;
    private String password;

}
