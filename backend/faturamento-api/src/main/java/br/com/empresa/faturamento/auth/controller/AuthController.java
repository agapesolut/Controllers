package br.com.empresa.faturamento.auth.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.faturamento.auth.dto.LoginRequest;
import br.com.empresa.faturamento.auth.service.AuthService;
import br.com.empresa.faturamento.shared.response.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.ok("Login realizado com sucesso.", authService.login(request));
    }

    @PostMapping("/logout")
    public ApiResponse<?> logout() {
        return ApiResponse.ok("Logout realizado com sucesso.", authService.logout());
    }

    @GetMapping("/me")
    public ApiResponse<?> me() {
        return ApiResponse.ok("Usuario autenticado.", authService.me());
    }
}
