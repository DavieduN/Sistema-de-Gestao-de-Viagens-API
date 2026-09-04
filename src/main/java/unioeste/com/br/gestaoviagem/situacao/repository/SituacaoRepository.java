package unioeste.com.br.gestaoviagem.situacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unioeste.com.br.gestaoviagem.situacao.domain.Situacao;

import java.util.Optional;

@Repository
public interface SituacaoRepository extends JpaRepository<Situacao, Integer> {
    Optional<Situacao> findByDescricao(String descricao);
}
