package com.ecommerce.api.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 5418991684586124597L;
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
