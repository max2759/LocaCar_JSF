package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "contracts", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Contracts.findContractByIdOrdersAndByIdCars", query = "SELECT c from ContractsEntity c where (c.ordersByIdOrders.id = :idOrder) and (c.carsByIdCars.id = :idCar)"),
        @NamedQuery(name = "Contracts.findAll", query = "SELECT c from ContractsEntity c "),
        @NamedQuery(name = "Contracts.countContractsByIdOrder", query = "SELECT COUNT(c) from ContractsEntity c where c.ordersByIdOrders.id = :idOrder"),
        @NamedQuery(name = "Contracts.findAllContractsByIdOrder", query = "SELECT c from ContractsEntity c where c.ordersByIdOrders.id = :idOrder"),
        @NamedQuery(name = "Contracts.findContractByIdCarAndTypeIsLeasing", query = "SELECT c from ContractsEntity c where c.carsByIdCars.id = :idCar and c.contractTypesByIdContractType.label = 'Leasing'"),
        @NamedQuery(name = "Contracts.findAllContractsByIdOrderAndDeadlineIsLowerThan1Month", query = "SELECT c from ContractsEntity c where (c.ordersByIdOrders.id = :idOrder) and (c.dateEnd < :todayPlus1Month) and (c.choiceEndLeasing = true)"),
})
public class ContractsEntity {
    private int id;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private boolean choiceEndLeasing;
    private Collection<ContractInsurancesEntity> contractInsurancesById;
    private OrdersEntity ordersByIdOrders;
    private CarsEntity carsByIdCars;
    private ContractTypesEntity contractTypesByIdContractType;
    private double carPrice;
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
    @Column(name = "Date_Start", nullable = false)
    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "Date_End")
    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
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
        return id == that.id && choiceEndLeasing == that.choiceEndLeasing && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateStart, dateEnd, choiceEndLeasing);
    }

    @OneToMany(mappedBy = "contractsByIdContract")
    public Collection<ContractInsurancesEntity> getContractInsurancesById() {
        return contractInsurancesById;
    }

    public void setContractInsurancesById(Collection<ContractInsurancesEntity> contractInsurancesById) {
        this.contractInsurancesById = contractInsurancesById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Orders", referencedColumnName = "id", nullable = false)
    public OrdersEntity getOrdersByIdOrders() {
        return ordersByIdOrders;
    }

    public void setOrdersByIdOrders(OrdersEntity ordersByIdOrders) {
        this.ordersByIdOrders = ordersByIdOrders;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars", referencedColumnName = "id", nullable = false)
    public CarsEntity getCarsByIdCars() {
        return carsByIdCars;
    }

    public void setCarsByIdCars(CarsEntity carsByIdCars) {
        this.carsByIdCars = carsByIdCars;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Contract_Type", referencedColumnName = "id", nullable = false)
    public ContractTypesEntity getContractTypesByIdContractType() {
        return contractTypesByIdContractType;
    }

    public void setContractTypesByIdContractType(ContractTypesEntity contractTypesByIdContractType) {
        this.contractTypesByIdContractType = contractTypesByIdContractType;
    }

    @Basic
    @Column(name = "Final_Price", nullable = false)
    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Basic
    @Column(name = "Car_Price", nullable = false)
    public double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }
}
