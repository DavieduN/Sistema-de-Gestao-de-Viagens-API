package unioeste.com.br.gestaoviagem.meiotransporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unioeste.com.br.gestaoviagem.meiotransporte.domain.MeioTransporte;

@Repository
public interface MeioTransporteRepository extends JpaRepository<MeioTransporte, Integer> {
}
