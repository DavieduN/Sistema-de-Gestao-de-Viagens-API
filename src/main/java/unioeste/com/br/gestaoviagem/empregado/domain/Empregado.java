package unioeste.com.br.gestaoviagem.empregado.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empregado")
@Setter @Getter
public class Empregado {
    @Id
    @Column(length = 50)
    private String matricula;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 100)
    private String area;
}