package egservis.services.dispositivo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import egservis.persistence.entities.Dispositivo;
import egservis.persistence.repository.DispositivoRepository;
import egservis.services.models.dto.dispositivo.DispositivoResponseDTO;
import egservis.services.models.dto.dispositivo.DispositivoUpdateDTO;
import egservis.services.models.exceptions.dispositivoExceptions.DispositivoNoExisteException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DispositivoServiceImp implements DispositivoService{

    private final DispositivoRepository dispositivoRepository;

    @Override
    public Page<DispositivoResponseDTO> getAll(Pageable pageable) {
        Page<DispositivoResponseDTO> page = dispositivoRepository.findAllPage(pageable);
        return page;
    }

    @Override
    public DispositivoResponseDTO getById(Long id) throws DispositivoNoExisteException {
        Optional<DispositivoResponseDTO> dispositivo = dispositivoRepository.findByIdDTO(id);
        if (!dispositivo.isPresent()) {
            throw new DispositivoNoExisteException("El dispositivo con el id " + id + " no existe");
        } 

        return dispositivo.get();
        
    }

    @Override
    public DispositivoResponseDTO update(Long id, DispositivoUpdateDTO dispositivoDTO) throws DispositivoNoExisteException {
        Optional <Dispositivo> dispositivo = dispositivoRepository.findById(id);

        if (!dispositivo.isPresent()) {
            throw new DispositivoNoExisteException("El dispositivo con el id " + id + " no existe");
        }
        dispositivo.get().actualizarDatos(dispositivoDTO);
        return new DispositivoResponseDTO(dispositivo.get());
    }

    @Override
    public void delete(Long id) {
        Optional<Dispositivo> dispositivo = dispositivoRepository.findById(id);
        dispositivo.ifPresent(dispositivoRepository::delete);
    }
    
}
