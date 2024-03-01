package egservis.services.dispositivo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import egservis.services.models.dto.dispositivo.DispositivoCountMothDTO;
import egservis.services.models.dto.dispositivo.DispositivoDTO;
import egservis.services.models.dto.dispositivo.DispositivoResponseDTO;
import egservis.services.models.dto.dispositivo.DispositivoUpdateDTO;
import egservis.services.models.dto.pedido.PedidoCompleteDTO;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import egservis.services.models.exceptions.dispositivoExceptions.DispositivoNoExisteException;
import jakarta.validation.Valid;


public interface DispositivoService {

    Page<DispositivoResponseDTO> getAll(Pageable pageable);

    DispositivoResponseDTO getById(Long id) throws DispositivoNoExisteException;

    DispositivoResponseDTO update(Long id, DispositivoUpdateDTO dispositivoDTO) throws DispositivoNoExisteException;

    void delete(Long id);

    DispositivoResponseDTO saveComplete(@Valid PedidoCompleteDTO pedidoDTO) throws ClienteNoExistenteException;

    DispositivoResponseDTO save(@Valid DispositivoDTO dispositivoDTO) throws ClienteNoExistenteException;

    Page<DispositivoResponseDTO> getByClienteId(Long id, Pageable pageable);

    Page<DispositivoResponseDTO> getByClienteDni(Long dni, Pageable pageable);

    List<DispositivoCountMothDTO> findAllByMes();

}