package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contract_insurances", schema = "locacarjsf", catalog = "")
public class ContractInsurancesEntity {
    private int id;
    private int idContract;
    private int idInsurance;
    private ContractsEntity contractsByIdContract;
    private InsurancesEntity insurancesByIdInsurance;

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
    @Column(name = "ID_Contract")
    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    @Basic
    @Column(name = "ID_Insurance")
    public int getIdInsurance() {
        return idInsurance;
    }

    public void setIdInsurance(int idInsurance) {
        this.idInsurance = idInsurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractInsurancesEntity that = (ContractInsurancesEntity) o;
        return id == that.id && idContract == that.idContract && idInsurance == that.idInsurance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idContract, idInsurance);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Contract", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public ContractsEntity getContractsByIdContract() {
        return contractsByIdContract;
    }

    public void setContractsByIdContract(ContractsEntity contractsByIdContract) {
        this.contractsByIdContract = contractsByIdContract;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Insurance", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public InsurancesEntity getInsurancesByIdInsurance() {
        return insurancesByIdInsurance;
    }

    public void setInsurancesByIdInsurance(InsurancesEntity insurancesByIdInsurance) {
        this.insurancesByIdInsurance = insurancesByIdInsurance;
    }
}
