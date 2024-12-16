package pe.gob.pj.depositos.domain.model.auditoriageneral;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PeticionServicios {
    String cuo;
    String ip;
    String usuario;
    String uri;
    String params;
    String herramienta;
    String ips;
    String jwt;
}
