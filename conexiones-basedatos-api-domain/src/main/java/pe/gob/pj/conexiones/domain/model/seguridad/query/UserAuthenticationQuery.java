package pe.gob.pj.conexiones.domain.model.seguridad.query;

public record UserAuthenticationQuery(

        String usuario,
        String clave,
        String codigoRol,
        String codigoCliente


) {
}
