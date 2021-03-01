package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "insurances", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Insurances.findAll", query = "SELECT i from InsurancesEntity i "),
        @NamedQuery(name = "Insurances.findByLabel", query = "SELECT i from InsurancesEntity i where i.label = :label"),
        @NamedQuery(name = "Insurances.findAllActiveInsurance", query = "SELECT i from InsurancesEntity i where i.active = true "),
})
public class InsurancesEntity {
    private int id;
    private String label;
    private String description;
    private double price;
    private boolean isActive;
    private Collection<ContractInsurancesEntity> contractInsurancesById;

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

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @NotNull
    @Column(name = "Price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "IsActive")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsurancesEntity that = (InsurancesEntity) o;
        return id == that.id && Double.compare(that.price, price) == 0 && isActive == that.isActive && Objects.equals(label, that.label) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, description, price, isActive);
    }

    @OneToMany(mappedBy = "insurancesByIdInsurance")
    public Collection<ContractInsurancesEntity> getContractInsurancesById() {
        return contractInsurancesById;
    }

    public void setContractInsurancesById(Collection<ContractInsurancesEntity> contractInsurancesById) {
        this.contractInsurancesById = contractInsurancesById;
    }
}
