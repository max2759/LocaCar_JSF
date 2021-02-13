package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "brands", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "brands.findAll", query = "SELECT b FROM BrandsEntity b order by b.label ASC"),
        @NamedQuery(name = "brands.findByLabel", query = "SELECT b FROM BrandsEntity b where b.label = :label")
})
public class BrandsEntity {
    private int id;
    private String label;
    private Collection<ModelsEntity> modelsById;

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
    @NotNull
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
        BrandsEntity that = (BrandsEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "brandsByIdBrands")
    public Collection<ModelsEntity> getModelsById() {
        return modelsById;
    }

    public void setModelsById(Collection<ModelsEntity> modelsById) {
        this.modelsById = modelsById;
    }
}
