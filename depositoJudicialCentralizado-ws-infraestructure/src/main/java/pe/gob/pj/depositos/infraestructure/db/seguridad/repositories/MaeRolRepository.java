package pe.gob.pj.depositos.infraestructure.db.seguridad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.pj.depositos.infraestructure.db.seguridad.entities.MaeRolEntity;

import java.util.List;

public interface MaeRolRepository extends JpaRepository<MaeRolEntity,Integer> {
    List<MaeRolEntity> findByActivoAndMaeRolUsuariosMaeUsuarioNUsuario(String activo, Integer nUsuario);
}
