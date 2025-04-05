package br.com.gerenciador.api.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ProjectDetails {

    @Value("${jwt.secret}")
    private String secret;

}
