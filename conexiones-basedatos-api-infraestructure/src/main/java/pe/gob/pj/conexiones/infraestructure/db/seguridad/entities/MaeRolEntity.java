package pe.gob.pj.conexiones.infraestructure.db.seguridad.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pe.gob.pj.conexiones.domain.common.utils.SecurityConstants;
import pe.gob.pj.conexiones.infraestructure.db.AuditoriaEntity;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "mae_rol", schema = SecurityConstants.ESQUEMA_SEGURIDAD)
public class MaeRolEntity extends AuditoriaEntity implements Serializable {
    @Id
    @Column(name = "n_rol")
    Integer numRol;

    @Column(name = "c_rol")
    String codRol;

    @Column(name = "x_descripcion")
    String descripcion;

    @Column(name = "x_rol")
    String rol;

    // bi-directional many-to-one association to MaeOperacion
    @OneToMany(mappedBy = "maeRol")
    List<MaeOperacionEntity> maeOperacions;

    // bi-directional many-to-one association to MaeRolUsuario
    @OneToMany(mappedBy = "maeRol")
    List<MaeRolUsuarioEntity> maeRolUsuarios;
}
