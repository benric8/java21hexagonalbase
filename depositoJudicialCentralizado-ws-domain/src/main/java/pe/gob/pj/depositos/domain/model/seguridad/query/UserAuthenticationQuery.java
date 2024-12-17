package pe.gob.pj.depositos.domain.model.seguridad.query;

public record UserAuthenticationQuery(

        String usuario,
        String clave,
        String codigoRol,
        String codigoCliente


) {
}
