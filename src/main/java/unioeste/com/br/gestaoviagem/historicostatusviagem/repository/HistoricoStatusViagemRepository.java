package unioeste.com.br.gestaoviagem.historicostatusviagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unioeste.com.br.gestaoviagem.historicostatusviagem.domain.HistoricoStatusViagem;

import java.util.List;

@Repository
public interface HistoricoStatusViagemRepository extends JpaRepository<HistoricoStatusViagem, Long> {

    List<HistoricoStatusViagem> findByViagemNumeroOrderByDataHoraDesc(Long numeroViagem);
}