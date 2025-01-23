package pe.gob.pj.conexiones.infraestructure.db.persistence;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import pe.gob.pj.conexiones.domain.common.utils.EncryptUtils;
import pe.gob.pj.conexiones.domain.common.utils.ProjectProperties;
import pe.gob.pj.conexiones.domain.model.seguridad.Rol;
import pe.gob.pj.conexiones.domain.model.seguridad.Usuario;
import pe.gob.pj.conexiones.domain.model.seguridad.query.UserAuthenticationQuery;
import pe.gob.pj.conexiones.domain.port.persistence.SeguridadPersistencePort;
import pe.gob.pj.conexiones.infraestructure.common.enums.Estado;
import pe.gob.pj.conexiones.infraestructure.db.seguridad.entities.MaeOperacionEntity;
import pe.gob.pj.conexiones.infraestructure.db.seguridad.entities.MaeRolUsuarioEntity;
import pe.gob.pj.conexiones.infraestructure.db.seguridad.entities.MaeUsuarioEntity;
import pe.gob.pj.conexiones.infraestructure.db.seguridad.repositories.MaeRolRepository;
import pe.gob.pj.conexiones.infraestructure.db.seguridad.repositories.MaeRolUsuarioRepository;
import pe.gob.pj.conexiones.infraestructure.db.seguridad.repositories.MaeUsuarioRepository;

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
        var claveGenerada = EncryptUtils.encrypt(query.usuario(), query.clave());
        return maeRolUsuarioRepository
                .autenticarUsuario(query.usuario(), claveGenerada,
                        query.codigoRol(), query.codigoCliente(), nAplicativo)
                .map(usuario -> usuario.getNumUsuario().toString()).orElse(null);
    }

    @Override
    public Usuario recuperaInfoUsuario(String cuo, String id) {
        return maeUsuarioRepository.findById(Integer.valueOf(id))
                .map(usuario -> new Usuario(usuario.getNumUsuario(),usuario.getCodUsuario(),usuario.getClave(),usuario.getActivo()))
                .orElse(null);
    }

    @Override
    public List<Rol> recuperarRoles(String cuo, String id) {
        return maeRolRepository
                .findMaeRolEntity(Estado.ACTIVO_NUMERICO.getNombre(),
                        Integer.parseInt(id))
                .stream().map(rol -> new Rol(rol.getNumRol(), rol.getCodRol(), rol.getRol(), rol.getActivo()))
                .toList();
    }

    @Override
    public Optional<String> validarAccesoMetodo(String cuo, String usuario, String rol, String operacion) {
        return maeRolUsuarioRepository.obtenerAccesoMetodos(usuario, rol).stream()
                .filter(op-> Pattern.compile(op.getEndpoint()).matcher(operacion).matches())
                .map(MaeOperacionEntity::getOperacion).findFirst();
    }
}
