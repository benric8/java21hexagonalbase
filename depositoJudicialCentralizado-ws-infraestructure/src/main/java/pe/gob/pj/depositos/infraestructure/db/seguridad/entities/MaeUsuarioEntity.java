package pe.gob.pj.depositos.infraestructure.db.seguridad.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pe.gob.pj.depositos.domain.common.utils.SecurityConstants;
import pe.gob.pj.depositos.infraestructure.db.AuditoriaEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name = "mae_usuario", schema = SecurityConstants.ESQUEMA_SEGURIDAD)
public class MaeUsuarioEntity extends AuditoriaEntity implements Serializable {
    @Id
    @Column(name = "n_usuario")
    Integer numUsuario;

    @Column(name = "c_clave")
    String clave;

    @Column(name = "c_usuario")
    String codUsuario;

    @Column(name = "f_registro")
    Timestamp fechaRegistro;

    // bi-directional many-to-one association to MaeRolUsuario
    @OneToMany(mappedBy = "maeUsuario")
    List<MaeRolUsuarioEntity> maeRolUsuarios;

    // bi-directional many-to-one association to MaeCliente
    @ManyToOne
    @JoinColumn(name = "n_cliente")
    MaeClienteEntity maeCliente;
}
