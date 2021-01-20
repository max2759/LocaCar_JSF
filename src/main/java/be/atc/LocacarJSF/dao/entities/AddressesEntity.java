package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses", schema = "locacarjsf", catalog = "")
public class AddressesEntity {
    private int id;
    private int idCities;
    private int idUsers;
    private String street;
    private String number;
    private String box;
    private CitiesEntity citiesByIdCities;
    private UsersEntity usersByIdUsers;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ID_Cities")
    public int getIdCities() {
        return idCities;
    }

    public void setIdCities(int idCities) {
        this.idCities = idCities;
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
    @Column(name = "Street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "Number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "Box")
    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressesEntity that = (AddressesEntity) o;
        return id == that.id && idCities == that.idCities && idUsers == that.idUsers && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(box, that.box);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCities, idUsers, street, number, box);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cities", referencedColumnName = "ID", nullable = false)
    public CitiesEntity getCitiesByIdCities() {
        return citiesByIdCities;
    }

    public void setCitiesByIdCities(CitiesEntity citiesByIdCities) {
        this.citiesByIdCities = citiesByIdCities;
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
