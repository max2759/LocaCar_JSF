package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "models", schema = "locacarjsf", catalog = "")
public class ModelsEntity {
    private int id;
    private int idBrands;
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
    @Column(name = "ID_Brands")
    public int getIdBrands() {
        return idBrands;
    }

    public void setIdBrands(int idBrands) {
        this.idBrands = idBrands;
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
        ModelsEntity that = (ModelsEntity) o;
        return id == that.id && idBrands == that.idBrands && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idBrands, label);
    }

    @OneToMany(mappedBy = "modelsByIdModels")
    public Collection<CarsEntity> getCarsById() {
        return carsById;
    }

    public void setCarsById(Collection<CarsEntity> carsById) {
        this.carsById = carsById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Brands", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public BrandsEntity getBrandsByIdBrands() {
        return brandsByIdBrands;
    }

    public void setBrandsByIdBrands(BrandsEntity brandsByIdBrands) {
        this.brandsByIdBrands = brandsByIdBrands;
    }
}
