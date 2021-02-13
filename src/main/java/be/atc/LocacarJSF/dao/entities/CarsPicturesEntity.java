package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cars_pictures", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "carsPictures.findAll", query = "SELECT cp FROM CarsPicturesEntity cp"),
        @NamedQuery(name = "carsPictures.findById", query = "SELECT cp FROM CarsPicturesEntity cp WHERE cp.id = :id")
})
public class CarsPicturesEntity {
    private int id;
    private String label;
    private Collection<CarsEntity> carsById;

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

    @OneToMany(mappedBy = "carsPicturesByIdCarsPictures")
    public Collection<CarsEntity> getCarsById() {
        return carsById;
    }

    public void setCarsById(Collection<CarsEntity> carsById) {
        this.carsById = carsById;
    }
}
