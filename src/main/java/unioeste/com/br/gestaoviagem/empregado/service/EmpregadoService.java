package unioeste.com.br.gestaoviagem.empregado.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.empregado.repository.EmpregadoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EmpregadoService {
    private final EmpregadoRepository empregadoRepository;

    public Empregado criar(Empregado empregado) {
        return empregadoRepository.save(empregado);
    }

    public List<Empregado> listarTodos() {
        return empregadoRepository.findAll();
    }

    public Empregado buscarPorMatricula(String matricula) {
        Empregado empregado = empregadoRepository.findById(matricula)
                .orElseThrow(() -> new IllegalArgumentException("Empregado não encontrado com a matrícula informada."));
        return empregado;
    }
}
