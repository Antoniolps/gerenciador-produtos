package br.com.gerenciador.api.controllers;

import br.com.gerenciador.api.dtos.AuthRequestDTO;
import br.com.gerenciador.api.dtos.AuthResponseDTO;
import br.com.gerenciador.api.dtos.RegisterRequestDTO;
import br.com.gerenciador.api.dtos.UsuarioResponseDTO;
import br.com.gerenciador.api.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Registrar novo usuário",
            description = "Cria um novo usuário com as credenciais fornecidas.")
    @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso",
            content = @Content(schema = @Schema(implementation = AuthResponseDTO.class)))
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(summary = "Login do usuário",
            description = "Autentica o usuário e retorna token JWT.")
    @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso",
            content = @Content(schema = @Schema(implementation = AuthResponseDTO.class)))
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(summary = "Obter dados do usuário logado",
            description = "Retorna os dados do usuário autenticado baseado no token JWT.")
    @ApiResponse(responseCode = "200", description = "Dados do usuário autenticado",
            content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class)))
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> getMe() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            return ResponseEntity.ok(authService.getUsuarioLogado(userDetails.getUsername()));
        }

        return ResponseEntity.status(401).build();
    }
}
