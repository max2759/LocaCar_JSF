package be.atc.LocacarJSF.dao.entities;

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
    private Object typeAds;

    @Id
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
        return id == adsEntity.id && idCars == adsEntity.idCars && isActive == adsEntity.isActive && Objects.equals(dateStart, adsEntity.dateStart) && Objects.equals(dateEnd, adsEntity.dateEnd) && Objects.equals(label, adsEntity.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCars, dateStart, dateEnd, label, isActive);
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

    @Basic
    @Column(name = "Price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Type_Ads")
    public Object getTypeAds() {
        return typeAds;
    }

    public void setTypeAds(Object typeAds) {
        this.typeAds = typeAds;
    }
}
