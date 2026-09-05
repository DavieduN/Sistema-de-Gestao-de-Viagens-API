package unioeste.com.br.gestaoviagem.motivo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unioeste.com.br.gestaoviagem.motivo.domain.Motivo;


@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Integer> {
}
