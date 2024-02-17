package egservis.services.dispositivo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import egservis.services.models.dto.dispositivo.DispositivoDTO;
import egservis.services.models.dto.dispositivo.DispositivoResponseDTO;
import egservis.services.models.dto.dispositivo.DispositivoUpdateDTO;

public class DispositivoServiceImp implements DispositivoService{

    @Override
    public DispositivoResponseDTO save(DispositivoDTO dispositivoDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Page<DispositivoResponseDTO> getAll(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public DispositivoResponseDTO getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public DispositivoResponseDTO update(Long id, DispositivoUpdateDTO dispositivoDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
