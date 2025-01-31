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
@Table(name = "mae_aplicativo", schema = SecurityConstants.ESQUEMA_SEGURIDAD)
public class MaeAplicativoEntity extends AuditoriaEntity implements Serializable {
    static final long serialVersionUID = 1L;


    @Id
    @Column(name = "n_aplicativo")
    Integer numAplicativo;

    @Column(name = "c_aplicativo")
    String codAplicativo;

    @Column(name = "x_aplicativo")
    String aplicativo;

    @Column(name = "x_descripcion")
    String descripcion;

    // bi-directional many-to-one association to MaeOperacion
    @OneToMany(mappedBy = "maeAplicativo")
    List<MaeOperacionEntity> maeOperacions;

    // bi-directional many-to-one association to MaeTipoAplicativo
 /*   @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "n_tipo_aplicativo")
    MaeTipoAplicativoEntity maeTipoAplicativo;*/



}
