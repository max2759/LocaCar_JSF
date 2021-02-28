package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Addresses.findAll", query = "SELECT a from AddressesEntity a"),
        @NamedQuery(name = "Adresses.findByIdUser", query = " select a from AddressesEntity a where a.usersByIdUsers.id = :idUser")
})
public class AddressesEntity {
    private int id;
    private String street;
    private String number;
    private String box;
    private CitiesEntity citiesByIdCities;
    private UsersEntity usersByIdUsers;

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
    @Column(name = "Street", nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "Number", nullable = false)
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
        return id == that.id && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(box, that.box);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, box);
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
