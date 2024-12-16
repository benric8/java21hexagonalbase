package pe.gob.pj.depositos.infraestructure.rest.responses;

import lombok.Data;
import pe.gob.pj.depositos.infraestructure.common.enums.TipoError;

import java.io.Serializable;

@Data
public class GlobalResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String descripcion;
    private String codigoOperacion;

    public GlobalResponse() {
        this.codigo = TipoError.OPERACION_EXITOSA.getCodigo();
        this.descripcion = TipoError.OPERACION_EXITOSA.getDescripcion();
    }
}
