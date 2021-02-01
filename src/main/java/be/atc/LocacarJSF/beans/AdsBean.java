package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.services.AdsServices;
import be.atc.LocacarJSF.services.AdsServicesImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "adsBean")
@RequestScoped
public class AdsBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -6795998607327751632L;

    AdsServices adsServices = new AdsServicesImpl();

}
