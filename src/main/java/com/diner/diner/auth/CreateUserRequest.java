package com.diner.diner.auth;

public record CreateUserRequest(
    String username,
     String password,
      String role) {}
