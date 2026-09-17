package unioeste.com.br.gestaoviagem.viagem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ViagemParams {
    private String destino;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<Long> idsSituacao;
}
