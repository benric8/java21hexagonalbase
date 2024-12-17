package pe.gob.pj.depositos.infraestructure.db.persistence;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import pe.gob.pj.depositos.domain.common.utils.EncryptUtils;
import pe.gob.pj.depositos.domain.common.utils.ProjectProperties;
import pe.gob.pj.depositos.domain.model.seguridad.Rol;
import pe.gob.pj.depositos.domain.model.seguridad.Usuario;
import pe.gob.pj.depositos.domain.model.seguridad.query.UserAuthenticationQuery;
import pe.gob.pj.depositos.domain.port.persistence.SeguridadPersistencePort;
import pe.gob.pj.depositos.infraestructure.common.enums.Estado;
import pe.gob.pj.depositos.infraestructure.db.seguridad.entities.MaeOperacionEntity;
import pe.gob.pj.depositos.infraestructure.db.seguridad.repositories.MaeRolRepository;
import pe.gob.pj.depositos.infraestructure.db.seguridad.repositories.MaeRolUsuarioRepository;
import pe.gob.pj.depositos.infraestructure.db.seguridad.repositories.MaeUsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
@FieldDefaults(level= AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SeguridadPersistenceAdapter implements SeguridadPersistencePort {

    final MaeRolRepository maeRolRepository;
    final MaeRolUsuarioRepository maeRolUsuarioRepository;
    final MaeUsuarioRepository maeUsuarioRepository;

    @Override
    public String autenticarUsuario(String cuo, UserAuthenticationQuery query) {
        var nAplicativo = ProjectProperties.getSeguridadIdAplicativo();
        return maeRolUsuarioRepository
                .autenticarUsuario(query.usuario(), EncryptUtils.encrypt(query.usuario(), query.clave()),
                        query.codigoRol(), query.codigoCliente(), nAplicativo)
                .map(usuario -> usuario.getNUsuario().toString()).orElse(null);
    }

    @Override
    public Usuario recuperaInfoUsuario(String cuo, String id) {
        return maeUsuarioRepository.findById(Integer.valueOf(id))
                .map(usuario -> new Usuario(usuario.getNUsuario(),usuario.getCUsuario(),usuario.getCClave(),usuario.getActivo()))
                .orElse(null);
    }

    @Override
    public List<Rol> recuperarRoles(String cuo, String id) {
        return maeRolRepository
                .findByActivoAndMaeRolUsuariosMaeUsuarioNUsuario(Estado.ACTIVO_NUMERICO.getNombre(),
                        Integer.parseInt(id))
                .stream().map(rol -> new Rol(rol.getNRol(), rol.getCRol(), rol.getXRol(), rol.getActivo()))
                .toList();
    }

    @Override
    public Optional<String> validarAccesoMetodo(String cuo, String usuario, String rol, String operacion) {
        return maeRolUsuarioRepository.obtenerAccesoMetodos(usuario, rol).stream()
                .filter(op-> Pattern.compile(op.getXEndpoint()).matcher(operacion).matches())
                .map(MaeOperacionEntity::getXOperacion).findFirst();
    }
}
