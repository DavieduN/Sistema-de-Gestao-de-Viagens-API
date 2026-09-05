package unioeste.com.br.gestaoviagem.cargo.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoForm {
    @NotBlank(message = "O nome do cargo é obrigatório")
    private String nome;
}
