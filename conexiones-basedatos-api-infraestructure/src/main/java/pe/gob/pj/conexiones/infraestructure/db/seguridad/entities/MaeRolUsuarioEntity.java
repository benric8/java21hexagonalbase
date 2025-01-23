package pe.gob.pj.conexiones.infraestructure.db.seguridad.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pe.gob.pj.conexiones.domain.common.utils.SecurityConstants;
import pe.gob.pj.conexiones.infraestructure.db.AuditoriaEntity;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name = "mae_rol_usuario", schema = SecurityConstants.ESQUEMA_SEGURIDAD)
public class MaeRolUsuarioEntity extends AuditoriaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "n_rol_usuario")
    Integer numRolUsuario;

    // bi-directional many-to-one association to MaeRol
    @ManyToOne  (fetch = FetchType.LAZY)
    @JoinColumn(name = "n_rol")
    MaeRolEntity maeRol;

    // bi-directional many-to-one association to MaeUsuario
    @ManyToOne  (fetch = FetchType.LAZY)
    @JoinColumn(name = "n_usuario")
    MaeUsuarioEntity maeUsuario;
}
