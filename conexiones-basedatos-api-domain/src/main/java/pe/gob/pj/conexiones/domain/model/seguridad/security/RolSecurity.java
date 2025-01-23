package pe.gob.pj.conexiones.domain.model.seguridad.security;

import java.util.Collections;
import java.util.List;

public record RolSecurity(Integer id, String name, List<UserSecurity> users) {
    public RolSecurity(Integer id, String name) {
        this(id, name, Collections.emptyList());
    }
}
