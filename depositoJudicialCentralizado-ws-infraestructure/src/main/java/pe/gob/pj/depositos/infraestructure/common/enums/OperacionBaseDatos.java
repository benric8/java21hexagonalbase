package pe.gob.pj.depositos.infraestructure.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperacionBaseDatos {
    INSERTAR("I"), ACTUALIZAR("U"), ELIMINAR("D");

    private final String nombre;

}
