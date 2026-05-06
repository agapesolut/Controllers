package br.com.empresa.faturamento.auth.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.empresa.faturamento.auth.dto.LoginRequest;
import br.com.empresa.faturamento.auth.dto.LoginResponse;
import br.com.empresa.faturamento.shared.util.MockDataFactory;
import br.com.empresa.faturamento.usuario.entity.Usuario;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final String adminPasswordHash;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.adminPasswordHash = passwordEncoder.encode("admin123");
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = MockDataFactory.admin();
        boolean loginValido = request.login().equalsIgnoreCase(usuario.getEmail()) || request.login().equalsIgnoreCase("admin");
        boolean senhaValida = passwordEncoder.matches(request.senha(), adminPasswordHash);

        if (!loginValido || !senhaValida) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais invalidas.");
        }

        return toLoginResponse(usuario);
    }

    public LoginResponse me() {
        return toLoginResponse(MockDataFactory.admin());
    }

    public String logout() {
        return "Sessao encerrada com sucesso.";
    }

    private LoginResponse toLoginResponse(Usuario usuario) {
        return new LoginResponse(
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getPerfil(),
            "mock-session-admin",
            usuario.getPermissoes()
        );
    }
}
