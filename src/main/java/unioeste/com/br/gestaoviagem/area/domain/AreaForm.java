package unioeste.com.br.gestaoviagem.area.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaForm {
    @NotBlank(message = "O nome da área é obrigatório")
    private String nome;
}
