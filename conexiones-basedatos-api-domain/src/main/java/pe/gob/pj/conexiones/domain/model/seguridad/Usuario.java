package pe.gob.pj.conexiones.domain.model.seguridad;

import java.io.Serializable;

public record Usuario(Integer id, String codUsuario, String clave, String activo) {

}
