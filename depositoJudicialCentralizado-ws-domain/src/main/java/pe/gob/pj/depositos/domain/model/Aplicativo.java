package pe.gob.pj.depositos.domain.model;

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
