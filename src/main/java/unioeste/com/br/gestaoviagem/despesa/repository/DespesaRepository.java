package unioeste.com.br.gestaoviagem.despesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unioeste.com.br.gestaoviagem.despesa.domain.Despesa;

import java.math.BigDecimal;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByViagemNumero(Long numeroViagem);

    @Query("SELECT COALESCE(SUM(d.valor), 0) FROM Despesa d")
    BigDecimal somarTotalGasto();
}