package unioeste.com.br.gestaoviagem.empregado.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import unioeste.com.br.gestaoviagem.area.domain.Area;
import unioeste.com.br.gestaoviagem.cargo.domain.Cargo;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "empregado")
public class Empregado implements UserDetails {

    @Id
    @Column(length = 50)
    private String matricula;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(nullable = false)
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Transforma o Cargo (Colaborador/Gestor) em uma ROLE do Spring
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.cargo.getNome().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.matricula;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}