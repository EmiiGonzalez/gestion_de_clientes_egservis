package egservis.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import egservis.persistence.entities.Dispositivo;
import egservis.services.models.dto.dispositivo.DispositivoCountMothDTO;
import egservis.services.models.dto.dispositivo.DispositivoResponseDTO;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    @Query("SELECT new egservis.services.models.dto.dispositivo.DispositivoResponseDTO(d) FROM Dispositivo d")
    Page<DispositivoResponseDTO> findAllPage(Pageable pageable);

    @Query("SELECT new egservis.services.models.dto.dispositivo.DispositivoResponseDTO(d) FROM Dispositivo d WHERE d.id=?1")
    Optional<DispositivoResponseDTO> findByIdDTO(Long id);

    @Query("SELECT new egservis.services.models.dto.dispositivo.DispositivoResponseDTO(d) FROM Dispositivo d WHERE d.cliente.id=?1")
    Page<DispositivoResponseDTO> findPageByClienteId(Long id, Pageable pageable);

    @Query("SELECT new egservis.services.models.dto.dispositivo.DispositivoResponseDTO(d) FROM Dispositivo d WHERE d.cliente.dni=?1")
    Page<DispositivoResponseDTO> findPageByClienteDni(Long dni, Pageable pageable);
    
    @Query("SELECT new egservis.services.models.dto.dispositivo.DispositivoCountMothDTO(MONTH(d.createdAt), COUNT(d)) FROM Dispositivo d WHERE YEAR(d.createdAt)=?1 GROUP BY MONTH(d.createdAt) ORDER BY MONTH(d.createdAt)")
    public List<DispositivoCountMothDTO> findAllByMes(Integer year);
} 