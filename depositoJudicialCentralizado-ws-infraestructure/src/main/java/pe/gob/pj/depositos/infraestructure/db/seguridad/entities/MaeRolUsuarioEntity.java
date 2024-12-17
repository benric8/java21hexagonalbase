package pe.gob.pj.depositos.infraestructure.db.seguridad.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pe.gob.pj.depositos.domain.common.utils.SecurityConstants;
import pe.gob.pj.depositos.infraestructure.db.AuditoriaEntity;

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
    Integer nRolUsuario;

    // bi-directional many-to-one association to MaeRol
    @ManyToOne
    @JoinColumn(name = "n_rol")
    MaeRolEntity maeRol;

    // bi-directional many-to-one association to MaeUsuario
    @ManyToOne
    @JoinColumn(name = "n_usuario")
    MaeUsuarioEntity maeUsuario;
}
