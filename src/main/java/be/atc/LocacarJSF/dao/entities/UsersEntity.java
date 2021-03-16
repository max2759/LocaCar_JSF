package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Users.findAll", query = "SELECT u from UsersEntity u"),
        @NamedQuery(name = "Users.findByUsername", query = "SELECT u from UsersEntity u where u.username = :username"),
        @NamedQuery(name = "Users.findByOneUsername", query = "SELECT u from UsersEntity u where u.username = :username"),
        @NamedQuery(name = "Users.connexion", query = "SELECT u from UsersEntity u where u.username = :username and u.password = :password"),
        @NamedQuery(name = "Users.findUserWithAddresses", query = "SELECT u FROM UsersEntity u join AddressesEntity a on u.id = a.usersByIdUsers.id where u.id = :idUser"),
        @NamedQuery(name = "Users.delete", query = "UPDATE UsersEntity u SET u.active = false where u.id = :idUser")
})
public class UsersEntity {

    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private LocalDateTime registerDate;
    private LocalDateTime birthdate;
    private String vatNumber;
    private boolean isActive;
    private Collection<AddressesEntity> addressesById;
    private Collection<OrdersEntity> ordersById;
    private RolesEntity rolesByIdRoles;
    private Collection<AdsEntity> adsById;

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
    @Column(name = "Firstname", nullable = false)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "Lastname", nullable = false)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "Username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Register_Date", nullable = false)
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "Birthdate", nullable = false)
    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
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
        return id == that.id && isActive == that.isActive && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(registerDate, that.registerDate) && Objects.equals(birthdate, that.birthdate) && Objects.equals(vatNumber, that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, username, password, registerDate, birthdate, vatNumber, isActive);
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
    @JoinColumn(name = "ID_Roles", referencedColumnName = "id", nullable = false)
    public RolesEntity getRolesByIdRoles() {
        return rolesByIdRoles;
    }

    public void setRolesByIdRoles(RolesEntity rolesByIdRoles) {
        this.rolesByIdRoles = rolesByIdRoles;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<AdsEntity> getAdsById() {
        return adsById;
    }

    public void setAdsById(Collection<AdsEntity> adsById) {
        this.adsById = adsById;
    }
}
