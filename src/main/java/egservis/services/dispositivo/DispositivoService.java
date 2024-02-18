package egservis.services.dispositivo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egservis.services.models.dto.dispositivo.DispositivoResponseDTO;
import egservis.services.models.dto.dispositivo.DispositivoUpdateDTO;
import egservis.services.models.exceptions.dispositivoExceptions.DispositivoNoExisteException;

@Service
public interface DispositivoService {


    Page<DispositivoResponseDTO> getAll(Pageable pageable);

    DispositivoResponseDTO getById(Long id) throws DispositivoNoExisteException;

    DispositivoResponseDTO update(Long id, DispositivoUpdateDTO dispositivoDTO) throws DispositivoNoExisteException;

    void delete(Long id);

}