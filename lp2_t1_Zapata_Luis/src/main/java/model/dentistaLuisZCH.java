package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Entity
@Table(name = "tbl_dentista")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class dentistaLuisZCH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dentista")
    private Integer idDentista;

    @Column(name = "cop", nullable = false, length = 6)
    private String cop;

    @Column(name = "nombre_completo", nullable = false, length = 50)
    private String nombreCompleto;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio_contrato", nullable = false)
    private Date fechaInicioContrato;

    @Column(name = "turno", nullable = false, length = 1)
    private String turno;

    @Column(name = "correo", nullable = false, length = 50, unique = true)
    private String correo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especialidad", nullable = false)
    private EspecialidadLuisZCH especialidad;

    // Sobrescribir toString para mostrar nombre legible en JComboBox
    @Override
    public String toString() {
        return nombreCompleto;
    }
}
