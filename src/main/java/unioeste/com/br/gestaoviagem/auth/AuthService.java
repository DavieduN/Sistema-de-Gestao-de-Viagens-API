package unioeste.com.br.gestaoviagem.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import unioeste.com.br.gestaoviagem.empregado.repository.EmpregadoRepository;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {
    private final EmpregadoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }
}
