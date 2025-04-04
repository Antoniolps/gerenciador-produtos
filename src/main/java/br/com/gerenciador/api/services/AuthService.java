package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.AuthRequestDTO;
import br.com.gerenciador.api.dtos.AuthResponseDTO;
import br.com.gerenciador.api.dtos.RegisterRequestDTO;
import br.com.gerenciador.api.dtos.UsuarioResponseDTO;
import br.com.gerenciador.api.enums.Role;
import br.com.gerenciador.api.models.Usuario;
import br.com.gerenciador.api.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO request) {
        Usuario usuario = Usuario.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();
        usuarioRepository.save(usuario);

        UserDetails userDetails = User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        UserDetails userDetails = User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);
        return new AuthResponseDTO(token);
    }

    public UsuarioResponseDTO getUsuarioLogado(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new UsuarioResponseDTO(
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
