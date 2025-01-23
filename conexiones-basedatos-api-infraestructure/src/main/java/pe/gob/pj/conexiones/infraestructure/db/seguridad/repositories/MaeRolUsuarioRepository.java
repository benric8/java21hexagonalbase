package pe.gob.pj.conexiones.infraestructure.db.seguridad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.pj.conexiones.infraestructure.db.seguridad.entities.MaeOperacionEntity;
import pe.gob.pj.conexiones.infraestructure.db.seguridad.entities.MaeRolUsuarioEntity;
import pe.gob.pj.conexiones.infraestructure.db.seguridad.entities.MaeUsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface MaeRolUsuarioRepository extends JpaRepository<MaeRolUsuarioEntity,Integer> {

    @Query(value = """
      SELECT u
      FROM MaeRolUsuarioEntity ru
      JOIN ru.maeRol r
      JOIN r.maeOperacions op
      JOIN op.maeAplicativo ap
      JOIN ru.maeUsuario u
      JOIN u.maeCliente c
      WHERE ru.activo = '1'
      AND u.activo = '1'
      AND r.activo = '1'
      AND ap.activo = '1'
      AND c.activo = '1'
      AND u.codUsuario= :cUsuario
      AND u.clave = :cClave
      AND r.codRol = :codRol
      AND ap.numAplicativo = :nAplicativo
      AND c.codCliente = :cCliente""")
    Optional<MaeUsuarioEntity> autenticarUsuario(@Param("cUsuario") String usuario,
                                                 @Param("cClave") String clave, @Param("codRol") String rol, @Param("cCliente") String cliente,
                                                 @Param("nAplicativo") Integer aplicativo);

    @Query(value = """
      SELECT op
      FROM MaeRolUsuarioEntity ru
      JOIN ru.maeRol r
      JOIN r.maeOperacions op
      JOIN ru.maeUsuario u
      WHERE u.activo = '1'
      AND ru.activo = '1'
      AND r.activo = '1'
      AND op.activo = '1'
      AND r.codRol = :codRol
      AND u.codUsuario = :cUsuario""")
    List<MaeOperacionEntity> obtenerAccesoMetodos(@Param("cUsuario") String usuario,
                                                  @Param("codRol") String rol);

}
