package unioeste.com.br.gestaoviagem.empregado.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unioeste.com.br.gestaoviagem.area.domain.Area;
import unioeste.com.br.gestaoviagem.cargo.domain.Cargo;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.empregado.domain.EmpregadoForm;
import unioeste.com.br.gestaoviagem.empregado.repository.EmpregadoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EmpregadoService {
    private final EmpregadoRepository empregadoRepository;

    // Injetando o encoder do Spring Security
    private final PasswordEncoder passwordEncoder;

    public Empregado criar(EmpregadoForm form, Cargo cargo, Area area) {
        if (empregadoRepository.existsById(form.getMatricula())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Matrícula já cadastrada.");
        }

        Empregado empregado = new Empregado();
        empregado.setMatricula(form.getMatricula());
        empregado.setNome(form.getNome());

        empregado.setSenha(passwordEncoder.encode(form.getSenha()));

        empregado.setCargo(cargo);
        empregado.setArea(area);

        return empregadoRepository.save(empregado);
    }

    public Empregado atualizar(Empregado empregado, EmpregadoForm form, Cargo cargo, Area area) {
        empregado.setNome(form.getNome());
        empregado.setCargo(cargo);
        empregado.setArea(area);

        return empregadoRepository.save(empregado);
    }

    public Empregado buscarPorMatricula(String matricula) {
        return empregadoRepository.findById(matricula)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Empregado com matrícula " + matricula + " não encontrado."
                ));
    }

    public List<Empregado> listarTodos() {
        return empregadoRepository.findAll();
    }

    public void deletar(Empregado empregado) {
        empregadoRepository.delete(empregado);
    }
}
