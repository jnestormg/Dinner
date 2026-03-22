package com.diner.diner.auth;

public record LoginRequest(
        String username,
        String password
) {}
