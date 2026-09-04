package unioeste.com.br.gestaoviagem.area.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unioeste.com.br.gestaoviagem.area.domain.Area;
import unioeste.com.br.gestaoviagem.area.domain.AreaForm;
import unioeste.com.br.gestaoviagem.area.repository.AreaRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AreaService {
    private final AreaRepository areaRepository;

    public Area criar(AreaForm form) {
        Area area = new Area();
        area.setNome(form.getNome());
        return areaRepository.save(area);
    }

    public List<Area> listarTodas() {
        return areaRepository.findAll();
    }

    public Area buscarPorId(Integer id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Área de ID " + id + " não encontrada."
                ));
    }

    public Area atualizar(Integer id, AreaForm form) {
        Area area = buscarPorId(id);
        area.setNome(form.getNome());
        return areaRepository.save(area);
    }

    public void deletar(Integer id) {
        Area area = buscarPorId(id);
        areaRepository.delete(area);
    }
}
