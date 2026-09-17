package unioeste.com.br.gestaoviagem.viagem.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import unioeste.com.br.gestaoviagem.viagem.domain.Viagem;
import unioeste.com.br.gestaoviagem.viagem.domain.ViagemParams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViagemSpecification {

    public static Specification<Viagem> comFiltros(ViagemParams params, String matriculaSolicitante) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params != null) {
                if (params.getDestino() != null && !params.getDestino().isBlank()) {
                    predicates.add(cb.like(cb.lower(root.get("destino")), "%" + params.getDestino().toLowerCase() + "%"));
                }
                if (params.getDataInicio() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("dataSaida"), params.getDataInicio()));
                }
                if (params.getDataFim() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("dataRetorno"), params.getDataFim()));
                }
                if (params.getIdsSituacao() != null && !params.getIdsSituacao().isEmpty()) {
                    predicates.add(root.join("situacao").get("id").in(params.getIdsSituacao()));
                }
            }

            if (matriculaSolicitante != null && !matriculaSolicitante.isBlank()) {
                predicates.add(cb.equal(root.join("solicitante").get("matricula"), matriculaSolicitante));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
