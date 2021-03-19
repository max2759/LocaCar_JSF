package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars_pictures", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "carsPictures.findAll", query = "SELECT cp FROM CarsPicturesEntity cp"),
        @NamedQuery(name = "carsPictures.findOnePicturesByIdCars", query = "SELECT cp FROM CarsPicturesEntity cp where cp.carsByIdCars.id = :idCars"),
        @NamedQuery(name = "carsPictures.findById", query = "SELECT cp FROM CarsPicturesEntity cp WHERE cp.id = :id"),
        @NamedQuery(name = "carsPictures.findByCarsId", query = "SELECT cp FROM CarsPicturesEntity cp WHERE cp.carsByIdCars.id = :id"),
        @NamedQuery(name = "carsPictures.findByLabel", query = "SELECT cp FROM CarsPicturesEntity cp WHERE cp.label = :label"),
        @NamedQuery(name = "carsPictures.findCarsPicturesByIdCars", query = "SELECT cp from CarsPicturesEntity cp where cp.carsByIdCars.id = :id")

})
public class CarsPicturesEntity {
    private int id;
    private String label;
    private CarsEntity carsByIdCars;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Label", nullable = false)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarsPicturesEntity that = (CarsPicturesEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars", referencedColumnName = "ID", nullable = false)
    public CarsEntity getCarsByIdCars() {
        return carsByIdCars;
    }

    public void setCarsByIdCars(CarsEntity carsByIdCars) {
        this.carsByIdCars = carsByIdCars;
    }
}
