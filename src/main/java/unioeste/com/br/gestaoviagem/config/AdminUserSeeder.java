package unioeste.com.br.gestaoviagem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import unioeste.com.br.gestaoviagem.area.domain.Area;
import unioeste.com.br.gestaoviagem.area.repository.AreaRepository;
import unioeste.com.br.gestaoviagem.cargo.domain.Cargo;
import unioeste.com.br.gestaoviagem.cargo.repository.CargoRepository;
import unioeste.com.br.gestaoviagem.empregado.domain.Empregado;
import unioeste.com.br.gestaoviagem.empregado.repository.EmpregadoRepository;

@Component
public class AdminUserSeeder implements CommandLineRunner {

    private final EmpregadoRepository empregadoRepository;
    private final CargoRepository cargoRepository;
    private final AreaRepository areaRepository;
    private final PasswordEncoder passwordEncoder;

    // Lendo os valores do application.yml
    @Value("${admin.matricula}")
    private String adminMatricula;

    @Value("${admin.nome}")
    private String adminNome;

    @Value("${admin.senha}")
    private String adminSenha;

    public AdminUserSeeder(EmpregadoRepository empregadoRepository,
                           CargoRepository cargoRepository,
                           AreaRepository areaRepository,
                           PasswordEncoder passwordEncoder) {
        this.empregadoRepository = empregadoRepository;
        this.cargoRepository = cargoRepository;
        this.areaRepository = areaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (empregadoRepository.findById(adminMatricula).isEmpty()) {
            Cargo cargoAdmin = cargoRepository.findById(2).orElseGet(() -> {
                Cargo cargo = new Cargo();
                cargo.setNome("Gestor");
                return cargoRepository.save(cargo);
            });

            Area areaAdmin = areaRepository.findById(1).orElseGet(() -> {
                Area area = new Area();
                area.setNome("Administração");
                return areaRepository.save(area);
            });

            Empregado admin = new Empregado();
            admin.setMatricula(adminMatricula);
            admin.setNome(adminNome);
            admin.setSenha(passwordEncoder.encode(adminSenha));
            admin.setCargo(cargoAdmin);
            admin.setArea(areaAdmin);

            empregadoRepository.save(admin);

            System.out.println("Usuário Admin criado com sucesso!");
        } else {
            System.out.println("Usuário Admin já existente. Inicialização ignorada.");
        }
    }
}
