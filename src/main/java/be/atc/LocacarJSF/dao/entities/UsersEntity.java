package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "locacarjsf", catalog = "")
public class UsersEntity {
    private int id;
    private int idRoles;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Date registerDate;
    private Date birthdate;
    private String vatNumber;
    private boolean isActive;
    private Collection<AddressesEntity> addressesById;
    private Collection<OrdersEntity> ordersById;
    private RolesEntity rolesByIdRoles;
    private Collection<UsersAdsEntity> usersAdsById;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ID_Roles")
    public int getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(int idRoles) {
        this.idRoles = idRoles;
    }

    @Basic
    @Column(name = "Firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "Lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Register_Date")
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "Birthdate")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "VATNumber")
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
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
        UsersEntity that = (UsersEntity) o;
        return id == that.id && idRoles == that.idRoles && isActive == that.isActive && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(registerDate, that.registerDate) && Objects.equals(birthdate, that.birthdate) && Objects.equals(vatNumber, that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRoles, firstname, lastname, username, password, registerDate, birthdate, vatNumber, isActive);
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<AddressesEntity> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<AddressesEntity> addressesById) {
        this.addressesById = addressesById;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<OrdersEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrdersEntity> ordersById) {
        this.ordersById = ordersById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Roles", referencedColumnName = "ID", nullable = false)
    public RolesEntity getRolesByIdRoles() {
        return rolesByIdRoles;
    }

    public void setRolesByIdRoles(RolesEntity rolesByIdRoles) {
        this.rolesByIdRoles = rolesByIdRoles;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<UsersAdsEntity> getUsersAdsById() {
        return usersAdsById;
    }

    public void setUsersAdsById(Collection<UsersAdsEntity> usersAdsById) {
        this.usersAdsById = usersAdsById;
    }
}
