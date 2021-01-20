package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_ads", schema = "locacarjsf", catalog = "")
public class UsersAdsEntity {
    private int id;
    private int idUsers;
    private int idAds;
    private UsersEntity usersByIdUsers;
    private AdsEntity adsByIdAds;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "ID_Ads")
    public int getIdAds() {
        return idAds;
    }

    public void setIdAds(int idAds) {
        this.idAds = idAds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersAdsEntity that = (UsersAdsEntity) o;
        return id == that.id && idUsers == that.idUsers && idAds == that.idAds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsers, idAds);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Users", referencedColumnName = "ID", nullable = false)
    public UsersEntity getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(UsersEntity usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Ads", referencedColumnName = "ID", nullable = false)
    public AdsEntity getAdsByIdAds() {
        return adsByIdAds;
    }

    public void setAdsByIdAds(AdsEntity adsByIdAds) {
        this.adsByIdAds = adsByIdAds;
    }
}
