package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cities", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Cities.findAll", query = "SELECT c from CitiesEntity c"),
        @NamedQuery(name = "Cities.findByLabel", query = "select c from CitiesEntity c where c.label = :label"),
        @NamedQuery(name = "Cities.findByUser", query = "SELECT c FROM CitiesEntity c join AddressesEntity a on a.citiesByIdCities.id =  c.id join UsersEntity u on a.usersByIdUsers.id = u.id where u.id = :idUser ")
})
public class CitiesEntity {
    private int id;
    private String region;
    private int postalCode;
    private String label;
    private Collection<AddressesEntity> addressesById;
    private CountriesEntity countriesByIdCountries;

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
    @Column(name = "Region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "Postal_Code", nullable = false)
    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "Label", nullable = false)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitiesEntity that = (CitiesEntity) o;
        return id == that.id && postalCode == that.postalCode && Objects.equals(region, that.region) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, region, postalCode, label);
    }

    @OneToMany(mappedBy = "citiesByIdCities")
    public Collection<AddressesEntity> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<AddressesEntity> addressesById) {
        this.addressesById = addressesById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Countries", referencedColumnName = "id", nullable = false)
    public CountriesEntity getCountriesByIdCountries() {
        return countriesByIdCountries;
    }

    public void setCountriesByIdCountries(CountriesEntity countriesByIdCountries) {
        this.countriesByIdCountries = countriesByIdCountries;
    }

    @Override
    public String toString() {
        return "CitiesEntity{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", postalCode=" + postalCode +
                ", label='" + label + '\'' +
                ", addressesById=" + addressesById +
                ", countriesByIdCountries=" + countriesByIdCountries +
                '}';
    }
}
