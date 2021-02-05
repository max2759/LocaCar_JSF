package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.services.AdsServices;
import be.atc.LocacarJSF.services.AdsServicesImpl;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "adsBean")
@SessionScoped
public class AdsBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -6795998607327751632L;

    AdsServices adsServices = new AdsServicesImpl();
    AdsEntity adsEntity;


    public AdsEntity getAdsEntity() {
        return adsEntity;
    }

    public void setAdsEntity(AdsEntity adsEntity) {
        this.adsEntity = adsEntity;
    }
}
