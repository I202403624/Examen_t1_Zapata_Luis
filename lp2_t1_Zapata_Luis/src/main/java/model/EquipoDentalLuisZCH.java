package model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;



@Entity
@Table(name = "tbl_equipo_dental")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EquipoDentalLuisZCH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_equipo")
    private Integer nroEquipo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "costo", nullable = false, precision = 10, scale = 2)
    private BigDecimal costo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_adquisicion", nullable = false)
    private Date fechaAdquisicion;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dentista")
    private dentistaLuisZCH dentista;
}
