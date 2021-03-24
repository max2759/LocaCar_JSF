package be.atc.LocacarJSF.dao.entities;

import be.atc.LocacarJSF.enums.EnumTypeAds;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ads", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Ads.findAll", query = "SELECT a from AdsEntity a where a.active=true and a.carsByIdCars.active=true order by a.id DESC"),
        @NamedQuery(name = "ads.findAdsByPrice", query = "SELECT a from AdsEntity a where a.price <= :price and a.active=true and a.carsByIdCars.active=true"),
        @NamedQuery(name = "ads.findAdsByIdUser", query = "SELECT a from AdsEntity a where a.usersByIdUsers.id = :id and a.active=true and a.carsByIdCars.active=true"),
        @NamedQuery(name = "ads.findAdsByTypeAds", query = "SELECT a from AdsEntity a where a.typeAds = :typeAds and a.active=true and a.carsByIdCars.active=true"),
        @NamedQuery(name = "ads.findAllDisabledAds", query = "SELECT a from AdsEntity a where a.active=false and a.carsByIdCars.active=false "),
        @NamedQuery(name = "ads.findDisabledAdsByUser", query = "SELECT a from AdsEntity a where a.usersByIdUsers.id = :id and a.active=false and a.carsByIdCars.active=false"),
        @NamedQuery(name = "ads.findAdsByModels", query = "SELECT a from AdsEntity a where a.carsByIdCars.modelsByIdModels.id = :id and a.active=true and a.carsByIdCars.active=true"),
        @NamedQuery(name = "ads.findByLabel", query = "select a from AdsEntity a where ( lower(a.label) like lower(concat('%', :label ,'%')) or lower(a.carsByIdCars.modelsByIdModels.label) like lower(concat('%', :label ,'%') ) or lower(a.carsByIdCars.modelsByIdModels.brandsByIdBrands.label) like lower(concat('%', :label ,'%') ) or lower(a.price) like lower(concat('%', :label ,'%') )) and a.active = true and a.carsByIdCars.active=true"),
        @NamedQuery(name = "ads.findAdsByModelsAndPrice", query = "select a from AdsEntity a where a.carsByIdCars.modelsByIdModels.id = :idModels and a.price <= :price and a.active = true and a.carsByIdCars.active=true"),
        @NamedQuery(name = "ads.findAdsByPriceAndTypeAds", query = "select a from AdsEntity a where a.typeAds = :enumTypeAds and a.price <= :price and a.active = true and a.carsByIdCars.active=true"),
        @NamedQuery(name = "ads.findAdsByModelAndTypeAds", query = "select a from AdsEntity a where a.typeAds = :enumTypeAds and a.carsByIdCars.modelsByIdModels.id = :idModel and a.active = true and a.carsByIdCars.active=true"),
        @NamedQuery(name = "ads.findAdsByModelsAndPriceAndTypeAds", query = "select a from AdsEntity a where a.typeAds = :enumTypeAds and a.carsByIdCars.modelsByIdModels.id = :idModel and a.price <= :price and a.active = true and a.carsByIdCars.active=true")
})
public class AdsEntity {
    private int id;

    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private String label;
    private boolean isActive;
    private CarsEntity carsByIdCars;
    private UsersEntity usersByIdUsers;
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
    @Column(name = "Date_Start", nullable = false)
    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "Date_End", nullable = false)
    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
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

    @ManyToOne
    @JoinColumn(name = "ID_Users", referencedColumnName = "ID", nullable = false)
    public UsersEntity getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(UsersEntity usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }
}
