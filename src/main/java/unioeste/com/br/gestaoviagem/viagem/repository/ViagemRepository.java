package unioeste.com.br.gestaoviagem.viagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;

import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long>, JpaSpecificationExecutor<Viagem> {
    List<Viagem> findBySolicitanteMatricula(String matricula);
    List<Viagem> findBySituacaoDescricao(String descricaoSituacao);

    Long countBySituacaoDescricaoIgnoreCase(String situacao);

    @Query(value = """
        SELECT v.destino 
        FROM svg.viagem v
        INNER JOIN svg.situacao s ON v.situacao_id = s.id
        WHERE s.descricao = 'Aprovada'
        GROUP BY v.destino 
        ORDER BY COUNT(v.destino) DESC 
        LIMIT 1
        """, nativeQuery = true)
    String findDestinoMaisVisitado();
}
