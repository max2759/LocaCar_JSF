package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "options", schema = "locacarjsf", catalog = "")
public class OptionsEntity {
    private int id;
    private String label;
    private Collection<CarsOptionsEntity> carsOptionsById;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Label")
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
        OptionsEntity that = (OptionsEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "optionsByIdOptions")
    public Collection<CarsOptionsEntity> getCarsOptionsById() {
        return carsOptionsById;
    }

    public void setCarsOptionsById(Collection<CarsOptionsEntity> carsOptionsById) {
        this.carsOptionsById = carsOptionsById;
    }
}
