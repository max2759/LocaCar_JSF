package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "models", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Models.findAll", query = "SELECT m FROM ModelsEntity m"),
        @NamedQuery(name = "Models.findByLabel", query = "SELECT m from ModelsEntity m where m.label = :label"),
        @NamedQuery(name = "Models.findModelsByBrands", query = "SELECT m FROM ModelsEntity m where m.brandsByIdBrands.id = :idBrands")
})
public class ModelsEntity {
    private int id;

    private String label;
    private Collection<CarsEntity> carsById;
    private BrandsEntity brandsByIdBrands;

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
        ModelsEntity that = (ModelsEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "modelsByIdModels")
    public Collection<CarsEntity> getCarsById() {
        return carsById;
    }

    public void setCarsById(Collection<CarsEntity> carsById) {
        this.carsById = carsById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Brands", referencedColumnName = "id", nullable = false)
    public BrandsEntity getBrandsByIdBrands() {
        return brandsByIdBrands;
    }

    public void setBrandsByIdBrands(BrandsEntity brandsByIdBrands) {
        this.brandsByIdBrands = brandsByIdBrands;
    }
}
