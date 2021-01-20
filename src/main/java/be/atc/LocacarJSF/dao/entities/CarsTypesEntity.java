package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cars_types", schema = "locacarjsf", catalog = "")
public class CarsTypesEntity {
    private int id;
    private String label;
    private int seatsNumbers;
    private int doorsNumbers;
    private Collection<CarsEntity> carsById;

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

    @Basic
    @Column(name = "Seats_Numbers")
    public int getSeatsNumbers() {
        return seatsNumbers;
    }

    public void setSeatsNumbers(int seatsNumbers) {
        this.seatsNumbers = seatsNumbers;
    }

    @Basic
    @Column(name = "Doors_Numbers")
    public int getDoorsNumbers() {
        return doorsNumbers;
    }

    public void setDoorsNumbers(int doorsNumbers) {
        this.doorsNumbers = doorsNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarsTypesEntity that = (CarsTypesEntity) o;
        return id == that.id && seatsNumbers == that.seatsNumbers && doorsNumbers == that.doorsNumbers && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, seatsNumbers, doorsNumbers);
    }

    @OneToMany(mappedBy = "carsTypesByIdCarsTypes")
    public Collection<CarsEntity> getCarsById() {
        return carsById;
    }

    public void setCarsById(Collection<CarsEntity> carsById) {
        this.carsById = carsById;
    }
}
