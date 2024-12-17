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
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "mae_tipo_aplicativo", schema = SecurityConstants.ESQUEMA_SEGURIDAD)
public class MaeTipoAplicativoEntity extends AuditoriaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "n_tipo_aplicativo")
    Integer nTipoAplicativo;

    @Column(name = "x_descripcion")
    String xDescripcion;

    @Column(name = "x_tipo_aplicativo")
    String xTipoAplicativo;

    // bi-directional many-to-one association to MaeAplicativo
    @OneToMany(mappedBy = "maeTipoAplicativo")
    List<MaeAplicativoEntity> maeAplicativos;
}
