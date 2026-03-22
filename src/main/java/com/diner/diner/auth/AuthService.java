package com.diner.diner.auth;



import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.diner.diner.entities.Usuario;
import com.diner.diner.repositories.UsuarioRepository;
import com.diner.diner.security.CustomUserDetailsService;
import com.diner.diner.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

   public AuthResponse login(LoginRequest request) {

    Usuario user = repository.findByUsername(request.username())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    if (!encoder.matches(request.password(), user.getPassword())) {
        throw new RuntimeException("Credenciales incorrectas");
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

    String accessToken = jwtService.generateToken(userDetails);

    // 🔥 por ahora simple (luego hacemos refresh real)
    String refreshToken = accessToken;

    return new AuthResponse(accessToken, refreshToken);
}
}