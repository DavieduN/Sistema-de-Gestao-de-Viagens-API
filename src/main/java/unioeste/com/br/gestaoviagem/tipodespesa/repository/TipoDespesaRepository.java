package unioeste.com.br.gestaoviagem.tipodespesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unioeste.com.br.gestaoviagem.tipodespesa.domain.TipoDespesa;

public interface TipoDespesaRepository extends JpaRepository<TipoDespesa, Integer> {
}
