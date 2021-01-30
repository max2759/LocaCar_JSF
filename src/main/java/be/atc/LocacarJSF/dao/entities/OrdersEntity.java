package be.atc.LocacarJSF.dao.entities;

import be.atc.LocacarJSF.enums.EnumOrderStatut;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Orders.findByIdUsersAndStatusIsPending", query = "SELECT o from OrdersEntity o where (o.idUsers = :idUser) and (o.orderStatut = be.atc.LocacarJSF.enums.EnumOrderStatut.Pending) "),
        @NamedQuery(name = "Orders.findAll", query = "SELECT o from OrdersEntity o "),
})
public class OrdersEntity {
    private int id;
    private int idUsers;
    private Date orderDate;
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
    @Column(name = "ID_Users")
    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    @Basic
    @Temporal(value = TemporalType.DATE)
    @Column(name = "Order_Date")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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
        return id == that.id && idUsers == that.idUsers && Objects.equals(orderDate, that.orderDate) && Objects.equals(orderStatut, that.orderStatut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsers, orderDate, orderStatut);
    }

    @OneToMany(mappedBy = "ordersByIdOrders")
    public Collection<ContractsEntity> getContractsById() {
        return contractsById;
    }

    public void setContractsById(Collection<ContractsEntity> contractsById) {
        this.contractsById = contractsById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Users", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public UsersEntity getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(UsersEntity usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }

}
