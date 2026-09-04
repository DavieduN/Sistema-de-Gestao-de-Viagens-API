package unioeste.com.br.gestaoviagem.cargo.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unioeste.com.br.gestaoviagem.cargo.domain.Cargo;
import unioeste.com.br.gestaoviagem.cargo.domain.CargoForm;
import unioeste.com.br.gestaoviagem.cargo.repository.CargoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;

    public Cargo criar(CargoForm form) {
        Cargo cargo = new Cargo();
        cargo.setNome(form.getNome());
        return cargoRepository.save(cargo);
    }

    public List<Cargo> listarTodos() {
        return cargoRepository.findAll();
    }

    public Cargo buscarPorId(Integer id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cargo de ID " + id + " não encontrado."
                ));
    }

    public Cargo atualizar(Integer id, CargoForm form) {
        Cargo cargo = buscarPorId(id);
        cargo.setNome(form.getNome());
        return cargoRepository.save(cargo);
    }

    public void deletar(Integer id) {
        Cargo cargo = buscarPorId(id);
        cargoRepository.delete(cargo);
    }
}
