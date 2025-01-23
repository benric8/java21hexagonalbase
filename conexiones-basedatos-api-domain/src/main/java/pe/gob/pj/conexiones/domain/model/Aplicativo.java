package pe.gob.pj.conexiones.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/*@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Aplicativo {
    String nombre;
    String estado;
    String version;
}*/
public record Aplicativo (String nombre,String estado, String version){}
