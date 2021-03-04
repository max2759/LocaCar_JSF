package be.atc.LocacarJSF.dao.entities;

import be.atc.LocacarJSF.enums.EnumOrderStatut;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Orders.findByIdUsersAndStatusIsPending", query = "SELECT o from OrdersEntity o where (o.usersByIdUsers.id = :idUser) and (o.orderStatut = be.atc.LocacarJSF.enums.EnumOrderStatut.Pending) "),
        @NamedQuery(name = "Orders.findAll", query = "SELECT o from OrdersEntity o "),
        @NamedQuery(name = "Orders.findAllByIdUsersAndStatusIsValidateOrCanceled", query = "SELECT o from OrdersEntity o where o.usersByIdUsers.id = :idUser and (o.orderStatut = be.atc.LocacarJSF.enums.EnumOrderStatut.Validate or o.orderStatut = be.atc.LocacarJSF.enums.EnumOrderStatut.Canceled)"),
        @NamedQuery(name = "Orders.findAllByUsernameUsersAndStatusIsValidateOrCanceled", query = "SELECT o from OrdersEntity o where (lower(o.usersByIdUsers.username) like lower(concat('%', :username,'%'))) and (o.orderStatut = be.atc.LocacarJSF.enums.EnumOrderStatut.Validate or o.orderStatut = be.atc.LocacarJSF.enums.EnumOrderStatut.Canceled)"),
        @NamedQuery(name = "Orders.findAllByIdOrderAndStatusIsValidateOrCanceled", query = "SELECT o from OrdersEntity o where o.id= :idOrder and (o.orderStatut = be.atc.LocacarJSF.enums.EnumOrderStatut.Validate or o.orderStatut = be.atc.LocacarJSF.enums.EnumOrderStatut.Canceled)"),
        @NamedQuery(name = "Orders.findAllOrdersByIdUserAndStatusIsValidate", query = "SELECT o from OrdersEntity o where o.usersByIdUsers.id= :idUser and (o.orderStatut = be.atc.LocacarJSF.enums.EnumOrderStatut.Validate)"),
})
public class OrdersEntity {
    private int id;
    private LocalDateTime orderDate;
    private Collection<ContractsEntity> contractsById;
    private UsersEntity usersByIdUsers;
    private EnumOrderStatut orderStatut;

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
    @Column(name = "Order_Date", nullable = false)
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Enumerated(EnumType.STRING)
    @Basic
    @Column(name = "Order_Statut", nullable = false)
    public EnumOrderStatut getOrderStatut() {
        return orderStatut;
    }

    public void setOrderStatut(EnumOrderStatut orderStatut) {
        this.orderStatut = orderStatut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return id == that.id && Objects.equals(orderDate, that.orderDate) && Objects.equals(orderStatut, that.orderStatut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, orderStatut);
    }

    @OneToMany(mappedBy = "ordersByIdOrders")
    public Collection<ContractsEntity> getContractsById() {
        return contractsById;
    }

    public void setContractsById(Collection<ContractsEntity> contractsById) {
        this.contractsById = contractsById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Users", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(UsersEntity usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }

}
