package unioeste.com.br.gestaoviagem.despesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unioeste.com.br.gestaoviagem.despesa.domain.Despesa;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByViagemNumero(Long numeroViagem);
}