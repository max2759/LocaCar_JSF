package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars_options", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "carsOptions.findAll", query = "SELECT co from CarsOptionsEntity co"),
        @NamedQuery(name = "carsOptions.findCarsOptionsByCarsId", query = "SELECT co from CarsOptionsEntity co where co.carsByIdCars.id = :idCars"),
        @NamedQuery(name = "carsOptions.oneCarsOptionsByCarsId", query = "SELECT co from CarsOptionsEntity co where co.carsByIdCars.id = :idCars"),
        @NamedQuery(name = "carsOptions.deleteByCarsID", query = "DELETE from CarsOptionsEntity co where co.carsByIdCars.id = :idCars"),
})
public class CarsOptionsEntity {
    private int id;
    private OptionsEntity optionsByIdOptions;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarsOptionsEntity that = (CarsOptionsEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Options", referencedColumnName = "id", nullable = false)
    public OptionsEntity getOptionsByIdOptions() {
        return optionsByIdOptions;
    }

    public void setOptionsByIdOptions(OptionsEntity optionsByIdOptions) {
        this.optionsByIdOptions = optionsByIdOptions;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars", referencedColumnName = "id", nullable = false)
    public CarsEntity getCarsByIdCars() {
        return carsByIdCars;
    }

    public void setCarsByIdCars(CarsEntity carsByIdCars) {
        this.carsByIdCars = carsByIdCars;
    }
}
