package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contract_insurances", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "ContractInsurances.findByIdContract", query = "SELECT ci from ContractInsurancesEntity ci where ci.contractsByIdContract.id = :idContract"),
})
public class ContractInsurancesEntity {
    private int id;
    private ContractsEntity contractsByIdContract;
    private InsurancesEntity insurancesByIdInsurance;
    private double insurancePrice;

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
        ContractInsurancesEntity that = (ContractInsurancesEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Contract", referencedColumnName = "id", nullable = false)
    public ContractsEntity getContractsByIdContract() {
        return contractsByIdContract;
    }

    public void setContractsByIdContract(ContractsEntity contractsByIdContract) {
        this.contractsByIdContract = contractsByIdContract;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Insurance", referencedColumnName = "id", nullable = false)
    public InsurancesEntity getInsurancesByIdInsurance() {
        return insurancesByIdInsurance;
    }

    public void setInsurancesByIdInsurance(InsurancesEntity insurancesByIdInsurance) {
        this.insurancesByIdInsurance = insurancesByIdInsurance;
    }

    @Basic
    @Column(name = "Insurance_Price", nullable = false)
    public double getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }
}
