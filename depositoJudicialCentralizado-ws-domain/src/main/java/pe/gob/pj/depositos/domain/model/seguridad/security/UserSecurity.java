package pe.gob.pj.depositos.domain.model.seguridad.security;

import java.util.List;

public record UserSecurity (Integer id, String name, String password,List<RolSecurity> roles) {

}
