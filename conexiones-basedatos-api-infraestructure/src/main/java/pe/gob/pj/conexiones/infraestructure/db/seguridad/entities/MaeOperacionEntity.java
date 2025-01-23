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
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "mae_operacion", schema = SecurityConstants.ESQUEMA_SEGURIDAD)
public class MaeOperacionEntity extends AuditoriaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "n_operacion")
    Integer numOperacion;

    @Column(name = "x_descripcion")
    String descripcion;

    @Column(name = "x_endpoint")
    String endpoint;

    @Column(name = "x_operacion")
    String operacion;

    // bi-directional many-to-one association to MaeAplicativo
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "n_aplicativo")
    MaeAplicativoEntity maeAplicativo;

    // bi-directional many-to-one association to MaeRol
    @ManyToOne  (fetch = FetchType.LAZY)
    @JoinColumn(name = "n_rol")
    MaeRolEntity maeRol;

}
