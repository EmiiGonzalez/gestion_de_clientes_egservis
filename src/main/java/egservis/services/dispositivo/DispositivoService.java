package egservis.services.dispositivo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egservis.services.models.dto.dispositivo.DispositivoDTO;
import egservis.services.models.dto.dispositivo.DispositivoResponseDTO;
import egservis.services.models.dto.dispositivo.DispositivoUpdateDTO;

@Service
public interface DispositivoService {

    DispositivoResponseDTO save(DispositivoDTO dispositivoDTO);

    Page<DispositivoResponseDTO> getAll(Pageable pageable);

    DispositivoResponseDTO getById(Long id);

    DispositivoResponseDTO update(Long id, DispositivoUpdateDTO dispositivoDTO);

}