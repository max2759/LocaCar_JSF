package be.atc.LocacarJSF.dao.entities;

import be.atc.LocacarJSF.enums.EnumOrderStatut;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "locacarjsf", catalog = "")
public class OrdersEntity {
    private int id;
    private int idUsers;
    private Date orderDate;
    private Collection<ContractsEntity> contractsById;
    private UsersEntity usersByIdUsers;
    private EnumOrderStatut orderStatut;

    @Id
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
    @JoinColumn(name = "ID_Users", referencedColumnName = "ID", nullable = false)
    public UsersEntity getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(UsersEntity usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }

}
