package be.atc.LocacarJSF.dao.entities;

import be.atc.LocacarJSF.enums.EnumTypeAds;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ads", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Ads.findAll", query = "SELECT a from AdsEntity a "),
})
public class AdsEntity {
    private int id;

    private Date dateStart;
    private Date dateEnd;
    private String label;
    private boolean isActive;
    private CarsEntity carsByIdCars;
    private Collection<UsersAdsEntity> usersAdsById;
    private double price;
    private EnumTypeAds typeAds;

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
    @Temporal(value = TemporalType.DATE)
    @Column(name = "Date_Start", nullable = false)
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Temporal(value = TemporalType.DATE)
    @Column(name = "Date_End", nullable = false)
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Basic
    @Column(name = "Label", nullable = false)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "Price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Enumerated(EnumType.STRING)
    @Basic
    @Column(name = "Type_Ads", nullable = false)
    public EnumTypeAds getTypeAds() {
        return typeAds;
    }

    public void setTypeAds(EnumTypeAds typeAds) {
        this.typeAds = typeAds;
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
        AdsEntity adsEntity = (AdsEntity) o;
        return id == adsEntity.id && Objects.equals(price, adsEntity.price) && isActive == adsEntity.isActive && Objects.equals(dateStart, adsEntity.dateStart) && Objects.equals(dateEnd, adsEntity.dateEnd) && Objects.equals(typeAds, adsEntity.typeAds) && Objects.equals(label, adsEntity.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, dateStart, dateEnd, typeAds, label, isActive);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars", referencedColumnName = "ID", nullable = false)
    public CarsEntity getCarsByIdCars() {
        return carsByIdCars;
    }

    public void setCarsByIdCars(CarsEntity carsByIdCars) {
        this.carsByIdCars = carsByIdCars;
    }

    @OneToMany(mappedBy = "adsByIdAds")
    public Collection<UsersAdsEntity> getUsersAdsById() {
        return usersAdsById;
    }

    public void setUsersAdsById(Collection<UsersAdsEntity> usersAdsById) {
        this.usersAdsById = usersAdsById;
    }


}
