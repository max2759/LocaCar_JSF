package be.atc.LocacarJSF.dao.entities;

import be.atc.LocacarJSF.enums.EnumTypeAds;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ads", schema = "locacarjsf", catalog = "")
public class AdsEntity {
    private int id;
    private int idCars;
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
    @Column(name = "ID_Cars")
    public int getIdCars() {
        return idCars;
    }

    public void setIdCars(int idCars) {
        this.idCars = idCars;
    }

    @Basic
    @Column(name = "Date_Start")
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "Date_End")
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Basic
    @Column(name = "Label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "Price")
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
        return id == adsEntity.id && Objects.equals(price, adsEntity.price) && idCars == adsEntity.idCars && isActive == adsEntity.isActive && Objects.equals(dateStart, adsEntity.dateStart) && Objects.equals(dateEnd, adsEntity.dateEnd) && Objects.equals(typeAds, adsEntity.typeAds) && Objects.equals(label, adsEntity.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, idCars, dateStart, dateEnd, typeAds, label, isActive);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars", referencedColumnName = "ID", insertable = false, updatable = false, nullable = false)
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
