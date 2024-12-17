package pe.gob.pj.depositos.domain.port.usecase;

import pe.gob.pj.depositos.domain.model.seguridad.Rol;
import pe.gob.pj.depositos.domain.model.seguridad.Usuario;
import pe.gob.pj.depositos.domain.model.seguridad.query.UserAuthenticationQuery;

import java.util.List;
import java.util.Optional;

public interface SeguridadUseCasePort {

    public String autenticarUsuario(String cuo, UserAuthenticationQuery query);

    public Usuario recuperaInfoUsuario(String cuo, String id);

    public List<Rol> recuperarRoles(String cuo, String id);

    public Optional<String> validarAccesoMetodo(String cuo, String usuario, String rol, String operacion);
}
