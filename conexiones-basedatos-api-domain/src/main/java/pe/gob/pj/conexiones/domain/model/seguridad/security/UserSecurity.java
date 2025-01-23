package pe.gob.pj.conexiones.domain.model.seguridad.security;

import java.util.List;

public record UserSecurity (Integer id, String name, String password,List<RolSecurity> roles) {

}
