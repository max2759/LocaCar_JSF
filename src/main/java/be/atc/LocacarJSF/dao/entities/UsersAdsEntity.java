package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_ads", schema = "locacarjsf", catalog = "")
public class UsersAdsEntity {
    private int id;
    private UsersEntity usersByIdUsers;
    private AdsEntity adsByIdAds;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersAdsEntity that = (UsersAdsEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Users", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public UsersEntity getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(UsersEntity usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Ads", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public AdsEntity getAdsByIdAds() {
        return adsByIdAds;
    }

    public void setAdsByIdAds(AdsEntity adsByIdAds) {
        this.adsByIdAds = adsByIdAds;
    }
}
