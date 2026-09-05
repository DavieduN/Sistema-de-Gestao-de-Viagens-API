package unioeste.com.br.gestaoviagem.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.security.TokenService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> efetuarLogin(@RequestBody Map<String, String> dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.get("matricula"), dados.get("senha"));
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Empregado) authentication.getPrincipal());

        return ResponseEntity.ok(Map.of("token", tokenJWT));
    }
}
