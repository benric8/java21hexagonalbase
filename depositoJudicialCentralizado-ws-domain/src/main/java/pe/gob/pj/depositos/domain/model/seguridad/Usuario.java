package pe.gob.pj.depositos.domain.model.seguridad;

import java.io.Serializable;

public record Usuario(Integer id, String codUsuario, String clave, String activo) {

}
