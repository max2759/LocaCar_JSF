package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars_options", schema = "locacarjsf", catalog = "")
public class CarsOptionsEntity {
    private int id;
    private int idOptions;
    private int idCars;
    private OptionsEntity optionsByIdOptions;
    private CarsEntity carsByIdCars;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ID_Options")
    public int getIdOptions() {
        return idOptions;
    }

    public void setIdOptions(int idOptions) {
        this.idOptions = idOptions;
    }

    @Basic
    @Column(name = "ID_Cars")
    public int getIdCars() {
        return idCars;
    }

    public void setIdCars(int idCars) {
        this.idCars = idCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarsOptionsEntity that = (CarsOptionsEntity) o;
        return id == that.id && idOptions == that.idOptions && idCars == that.idCars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idOptions, idCars);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Options", referencedColumnName = "ID", nullable = false)
    public OptionsEntity getOptionsByIdOptions() {
        return optionsByIdOptions;
    }

    public void setOptionsByIdOptions(OptionsEntity optionsByIdOptions) {
        this.optionsByIdOptions = optionsByIdOptions;
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
