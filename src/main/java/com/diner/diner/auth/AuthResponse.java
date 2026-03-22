package com.diner.diner.auth;

public record AuthResponse(
    String accessToken,
    String refreshToken
) {}