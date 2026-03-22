package com.diner.diner.auth;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.diner.diner.Enums.Rol;
import com.diner.diner.entities.Usuario;
import com.diner.diner.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public Usuario createUser(@RequestBody CreateUserRequest request) {

        // Verificar si el usuario ya existe
        if (usuarioRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        // Crear usuario y encriptar la contraseña
        Usuario user = new Usuario();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRol(Rol.valueOf(request.role())); // convierte String a enum
        // Guardar en la base de datos
        return usuarioRepository.save(user);
    }
}