package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cars_colors", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "CarsColors.findAll", query = "SELECT cc FROM CarsColorsEntity cc"),
        @NamedQuery(name = "CarsColors.findByLabel", query = "SELECT cc FROM CarsColorsEntity cc WHERE cc.label = :label")
})
public class CarsColorsEntity {
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
    @Column(name = "Label", nullable = false, unique = true)
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
        CarsColorsEntity that = (CarsColorsEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "carsColorsByIdCarsColors")
    public Collection<CarsEntity> getCarsById() {
        return carsById;
    }

    public void setCarsById(Collection<CarsEntity> carsById) {
        this.carsById = carsById;
    }
}
