package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "contracts", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Contracts.findContractByIdOrdersAndByIdCars", query = "SELECT c from ContractsEntity c where (c.idOrders = :idOrder) and (c.idCars = :idCar)"),
        @NamedQuery(name = "Contracts.findAll", query = "SELECT c from ContractsEntity c "),
})
public class ContractsEntity {
    private int id;
    private int idOrders;
    private int idCars;
    private int idContractType;
    private Date dateStart;
    private Date dateEnd;
    private boolean choiceEndLeasing;
    private Collection<ContractInsurancesEntity> contractInsurancesById;
    private OrdersEntity ordersByIdOrders;
    private CarsEntity carsByIdCars;
    private ContractTypesEntity contractTypesByIdContractType;
    private double finalPrice;

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
    @Column(name = "ID_Orders")
    public int getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(int idOrders) {
        this.idOrders = idOrders;
    }

    @Basic
    @Column(name = "ID_Cars")
    public int getIdCars() {
        return idCars;
    }

    public void setIdCars(int idCars) {
        this.idCars = idCars;
    }

    @Basic
    @Column(name = "ID_Contract_Type")
    public int getIdContractType() {
        return idContractType;
    }

    public void setIdContractType(int idContractType) {
        this.idContractType = idContractType;
    }

    @Basic
    @Temporal(value = TemporalType.DATE)
    @Column(name = "Date_Start")
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Temporal(value = TemporalType.DATE)
    @Column(name = "Date_End")
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Basic
    @Column(name = "Choice_end_Leasing")
    public boolean isChoiceEndLeasing() {
        return choiceEndLeasing;
    }

    public void setChoiceEndLeasing(boolean choiceEndLeasing) {
        this.choiceEndLeasing = choiceEndLeasing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractsEntity that = (ContractsEntity) o;
        return id == that.id && idOrders == that.idOrders && idCars == that.idCars && idContractType == that.idContractType && choiceEndLeasing == that.choiceEndLeasing && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idOrders, idCars, idContractType, dateStart, dateEnd, choiceEndLeasing);
    }

    @OneToMany(mappedBy = "contractsByIdContract")
    public Collection<ContractInsurancesEntity> getContractInsurancesById() {
        return contractInsurancesById;
    }

    public void setContractInsurancesById(Collection<ContractInsurancesEntity> contractInsurancesById) {
        this.contractInsurancesById = contractInsurancesById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Orders", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public OrdersEntity getOrdersByIdOrders() {
        return ordersByIdOrders;
    }

    public void setOrdersByIdOrders(OrdersEntity ordersByIdOrders) {
        this.ordersByIdOrders = ordersByIdOrders;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public CarsEntity getCarsByIdCars() {
        return carsByIdCars;
    }

    public void setCarsByIdCars(CarsEntity carsByIdCars) {
        this.carsByIdCars = carsByIdCars;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Contract_Type", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public ContractTypesEntity getContractTypesByIdContractType() {
        return contractTypesByIdContractType;
    }

    public void setContractTypesByIdContractType(ContractTypesEntity contractTypesByIdContractType) {
        this.contractTypesByIdContractType = contractTypesByIdContractType;
    }

    @Basic
    @Column(name = "Final_Price")
    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
