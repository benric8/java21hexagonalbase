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
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "mae_tipo_aplicativo", schema = SecurityConstants.ESQUEMA_SEGURIDAD)
public class MaeOperacionEntity extends AuditoriaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "n_operacion")
    Integer nOperacion;

    @Column(name = "x_descripcion")
    String xDescripcion;

    @Column(name = "x_endpoint")
    String xEndpoint;

    @Column(name = "x_operacion")
    String xOperacion;

    // bi-directional many-to-one association to MaeAplicativo
    @ManyToOne
    @JoinColumn(name = "n_aplicativo")
    MaeAplicativoEntity maeAplicativo;

    // bi-directional many-to-one association to MaeRol
    @ManyToOne
    @JoinColumn(name = "n_rol")
    MaeRolEntity maeRol;

}
