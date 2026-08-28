package unioeste.com.br.gestaoviagem.viagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {
}
