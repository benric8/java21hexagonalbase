package pe.gob.pj.conexiones.usecase;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.pj.conexiones.domain.model.seguridad.Rol;
import pe.gob.pj.conexiones.domain.model.seguridad.Usuario;
import pe.gob.pj.conexiones.domain.model.seguridad.query.UserAuthenticationQuery;
import pe.gob.pj.conexiones.domain.port.persistence.SeguridadPersistencePort;
import pe.gob.pj.conexiones.domain.port.usecase.SeguridadUseCasePort;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service("seguridadUseCasePort")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeguridadUseCaseAdapter implements SeguridadUseCasePort {

    final SeguridadPersistencePort seguridadPersistencePort;

    @Override
    @Transactional(transactionManager = "txManagerSeguridad", propagation = Propagation.REQUIRED,
            readOnly = true, rollbackFor = {Exception.class, SQLException.class})
    public String autenticarUsuario(String cuo, UserAuthenticationQuery query) {
        return seguridadPersistencePort.autenticarUsuario(cuo, query);
    }

    @Override
    @Transactional(transactionManager = "txManagerSeguridad", propagation = Propagation.REQUIRED,
            readOnly = true, rollbackFor = {Exception.class, SQLException.class})
    public Usuario recuperaInfoUsuario(String cuo, String id) {
        return seguridadPersistencePort.recuperaInfoUsuario(cuo, id);
    }

    @Override
    @Transactional(transactionManager = "txManagerSeguridad", propagation = Propagation.REQUIRED,
            readOnly = true, rollbackFor = {Exception.class, SQLException.class})
    public List<Rol> recuperarRoles(String cuo, String id) {
        return seguridadPersistencePort.recuperarRoles(cuo, id);
    }

    @Override
    @Transactional(transactionManager = "txManagerSeguridad", propagation = Propagation.REQUIRED,
            readOnly = true, rollbackFor = {Exception.class, SQLException.class})
    public Optional<String> validarAccesoMetodo(String cuo, String usuario, String rol, String operacion) {
        return seguridadPersistencePort.validarAccesoMetodo(cuo, usuario, rol, operacion);
    }
}
