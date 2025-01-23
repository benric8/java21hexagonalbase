package pe.gob.pj.conexiones.infraestructure.db.seguridad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.pj.conexiones.infraestructure.db.seguridad.entities.MaeRolEntity;

import java.util.List;

public interface MaeRolRepository extends JpaRepository<MaeRolEntity,Integer> {
    @Query("""
            SELECT r FROM MaeRolEntity r
            JOIN r.maeRolUsuarios ru
            JOIN ru.maeUsuario u
            WHERE r.activo = :activo AND u.numUsuario = :nUsuario""")
    List<MaeRolEntity> findMaeRolEntity(
          @Param("activo") String activo,
          @Param("nUsuario")  Integer nUsuario);
}
