package pe.gob.pj.conexiones.infraestructure.rest.responses;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import pe.gob.pj.conexiones.domain.model.Aplicativo;

import java.io.Serializable;

@EqualsAndHashCode(callSuper=true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AplicativoResponse extends GlobalResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    Aplicativo data;
}
