package pe.gob.pj.depositos.infraestructure.db.seguridad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.pj.depositos.infraestructure.db.seguridad.entities.MaeUsuarioEntity;

import java.util.Optional;

public interface MaeUsuarioRepository extends JpaRepository<MaeUsuarioEntity,Integer> {

    Optional<MaeUsuarioEntity> findByNumUsuarioAndActivo(Integer id, String activo);
}
