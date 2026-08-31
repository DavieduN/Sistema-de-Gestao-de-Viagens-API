package unioeste.com.br.gestaoviagem.empregado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, String> {
}
