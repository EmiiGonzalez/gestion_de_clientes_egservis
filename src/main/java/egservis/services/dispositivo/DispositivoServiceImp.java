package egservis.services.dispositivo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egservis.persistence.entities.Dispositivo;
import egservis.persistence.entities.Pedido;
import egservis.persistence.repository.DispositivoRepository;
import egservis.services.cliente.ClienteServiceImp;
import egservis.services.models.dto.dispositivo.DispositivoCountMothDTO;
import egservis.services.models.dto.dispositivo.DispositivoDTO;
import egservis.services.models.dto.dispositivo.DispositivoResponseDTO;
import egservis.services.models.dto.dispositivo.DispositivoUpdateDTO;
import egservis.services.models.dto.pedido.PedidoCompleteDTO;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import egservis.services.models.exceptions.dispositivoExceptions.DispositivoNoExisteException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DispositivoServiceImp implements DispositivoService{

    private final DispositivoRepository dispositivoRepository;
    private final ClienteServiceImp clienteService;

    //GET METHODS START
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
    public Page<DispositivoResponseDTO> getByClienteId(Long id, Pageable pageable) {
        Page<DispositivoResponseDTO> page = dispositivoRepository.findPageByClienteId(id, pageable);
        return page;
    }
    
    @Override
    public Page<DispositivoResponseDTO> getByClienteDni(Long dni, Pageable pageable) {
        Page<DispositivoResponseDTO> page = dispositivoRepository.findPageByClienteDni(dni, pageable);
        return page;
    }

    @Override
    public List<DispositivoCountMothDTO> findAllByMes() {
       return dispositivoRepository.findAllByMes();
    }
    //GET METHODS END
    
    //PUT METHODS START
    @Override
    public DispositivoResponseDTO update(Long id, DispositivoUpdateDTO dispositivoDTO) throws DispositivoNoExisteException {
        Optional <Dispositivo> dispositivo = dispositivoRepository.findById(id);

        if (!dispositivo.isPresent()) {
            throw new DispositivoNoExisteException("El dispositivo con el id " + id + " no existe");
        }
        dispositivo.get().actualizarDatos(dispositivoDTO);
        return new DispositivoResponseDTO(dispositivo.get());
    }
    //POST METHODS END

    //DELETE METHODS START

    @Override
    public void delete(Long id) {
        Optional<Dispositivo> dispositivo = dispositivoRepository.findById(id);
        dispositivo.ifPresent(dispositivoRepository::delete);
    }
    //DELETE METHODS END

    //POST METHODS START
    @Override
    public DispositivoResponseDTO saveComplete(@Valid PedidoCompleteDTO pedidoDTO) throws ClienteNoExistenteException {
        Dispositivo dispositivo = new Dispositivo(pedidoDTO);
        clienteService.addDispositivo(dispositivo, pedidoDTO.idCliente());
        Pedido p = new Pedido(pedidoDTO);

        dispositivo.addPedido(p);

        return new DispositivoResponseDTO(dispositivoRepository.save(dispositivo));
    }

    @Override
    public DispositivoResponseDTO save(@Valid DispositivoDTO dispositivoDTO) throws ClienteNoExistenteException {
        
        Dispositivo dispositivo = new Dispositivo(dispositivoDTO);
        clienteService.addDispositivo(dispositivo, dispositivoDTO.idCliente());

        return new DispositivoResponseDTO(dispositivoRepository.save(dispositivo));
    }
    //POST METHODS END

    public void addPedido(Pedido p, Long idDispositivo) throws DispositivoNoExisteException {
        Optional<Dispositivo> dispositivo = dispositivoRepository.findById(idDispositivo);

        if (!dispositivo.isPresent()) {
            throw new DispositivoNoExisteException("El dispositivo con el id " + idDispositivo + " no existe");
        }

        dispositivo.get().addPedido(p);
    }

}
