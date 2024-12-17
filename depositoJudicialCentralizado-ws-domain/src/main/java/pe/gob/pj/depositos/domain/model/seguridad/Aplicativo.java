package pe.gob.pj.depositos.domain.model.seguridad;

import java.io.Serializable;

/*public class Aplicativo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codAplicativo;
    private String nomAplicativo;
}*/
public record Aplicativo(String codAplicativo,String nomAplicativo){}