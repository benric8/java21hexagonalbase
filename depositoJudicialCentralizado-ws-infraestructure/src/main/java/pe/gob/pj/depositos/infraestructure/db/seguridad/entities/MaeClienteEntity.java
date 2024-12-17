package pe.gob.pj.depositos.infraestructure.db.seguridad.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pe.gob.pj.depositos.domain.common.utils.SecurityConstants;
import pe.gob.pj.depositos.infraestructure.db.AuditoriaEntity;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name = "mae_cliente", schema = SecurityConstants.ESQUEMA_SEGURIDAD)
public class MaeClienteEntity extends AuditoriaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "n_cliente")
    Integer nCliente;

    @Column(name = "c_cliente")
    String cCliente;

    @Column(name = "l_tipo_cliente")
    String lTipoCliente;

    @Column(name = "x_cliente")
    String xCliente;

    @Column(name = "x_descripcion")
    String xDescripcion;

    // bi-directional many-to-one association to MaeUsuario
    @OneToMany(mappedBy = "maeCliente")
    List<MaeUsuarioEntity> maeUsuarios;
}