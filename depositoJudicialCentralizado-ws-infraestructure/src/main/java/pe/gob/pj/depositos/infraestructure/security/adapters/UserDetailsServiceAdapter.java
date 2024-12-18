package pe.gob.pj.depositos.infraestructure.security.adapters;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.pj.depositos.domain.model.seguridad.security.RolSecurity;
import pe.gob.pj.depositos.domain.model.seguridad.security.UserSecurity;
import pe.gob.pj.depositos.domain.port.usecase.SeguridadUseCasePort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsServiceAdapter implements UserDetailsService {

    final SeguridadUseCasePort seguridadService;

    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var u = seguridadService.recuperaInfoUsuario("", username);
        if (Objects.isNull(u) || u.id() < 1) {
            log.error("No se pudo recuperar la información del usuario [{}].", username);
        }

        var roles = new ArrayList<RolSecurity>();
        var rolesB = seguridadService.recuperarRoles("", username);
        rolesB.forEach(rol -> roles.add(new RolSecurity(rol.id(), rol.codRol())));
        var user = new UserSecurity(u.id(),u.codUsuario(),passwordEncoder.encode(u.clave()),roles);

        return new User(user.name(), user.password(), getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(UserSecurity user) {
        String[] userRoles = user.roles().stream().map(RolSecurity::name).toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }
}
