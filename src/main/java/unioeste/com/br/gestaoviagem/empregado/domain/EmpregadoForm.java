package unioeste.com.br.gestaoviagem.empregado.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmpregadoForm {
    @NotBlank(message = "A matrícula é obrigatória")
    @Pattern(regexp = "^[0-9]{4}-[0-9]$", message = "A matrícula deve estar no formato XXXX-X (Ex: 1234-5)")
    private String matricula;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O ID do cargo é obrigatório")
    private Integer cargoId;

    @NotNull(message = "O ID da área é obrigatório")
    private Integer areaId;
}
