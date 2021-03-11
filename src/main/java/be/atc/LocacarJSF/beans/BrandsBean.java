package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.BrandsEntity;
import be.atc.LocacarJSF.services.BrandsServices;
import be.atc.LocacarJSF.services.BrandsServicesImpl;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


/**
 * @author Maximilien - Zabbara
 */
@Named(value = "brandsBean")
@ViewScoped
public class BrandsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -7131593542829187292L;

    private BrandsServices brandsServices = new BrandsServicesImpl();
    private BrandsEntity brandsEntity = new BrandsEntity();
    private List<BrandsEntity> brandsEntities;

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste optionsEntities
     */
    @PostConstruct
    public void init() {
        log.info("Init brands");
        brandsEntities = brandsServices.findAll();
    }


    ////// Getters and setters

    public BrandsServices getBrandsServices() {
        return brandsServices;
    }

    public void setBrandsServices(BrandsServices brandsServices) {
        this.brandsServices = brandsServices;
    }

    public BrandsEntity getBrandsEntity() {
        return brandsEntity;
    }

    public void setBrandsEntity(BrandsEntity brandsEntity) {
        this.brandsEntity = brandsEntity;
    }

    public List<BrandsEntity> getBrandsEntities() {
        return brandsEntities;
    }

    public void setBrandsEntities(List<BrandsEntity> brandsEntities) {
        this.brandsEntities = brandsEntities;
    }
}
