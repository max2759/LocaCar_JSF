package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cars_gearbox", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "carsGearbox.findAll", query = "SELECT cg FROM CarsGearboxEntity cg"),
        @NamedQuery(name = "carsGearbox.findByLabel", query = "SELECT cg FROM CarsGearboxEntity cg where cg.label = :label")
})
public class CarsGearboxEntity {
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
        CarsGearboxEntity that = (CarsGearboxEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "carsGearboxByIdCarsGearBox")
    public Collection<CarsEntity> getCarsById() {
        return carsById;
    }

    public void setCarsById(Collection<CarsEntity> carsById) {
        this.carsById = carsById;
    }
}
