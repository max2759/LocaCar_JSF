package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.OptionsEntity;
import be.atc.LocacarJSF.services.OptionsServices;
import be.atc.LocacarJSF.services.OptionsServicesImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "optionsBean")
@RequestScoped
public class OptionsBean implements Serializable {

    private static final long serialVersionUID = -5483371744376024574L;

    OptionsServices optionsServices = new OptionsServicesImpl();
    List<OptionsEntity> optionsEntities;

    @PostConstruct
    public void init() {
        optionsEntities = optionsServices.findAll();
    }

    public List<OptionsEntity> getOptionsEntities() {
        return optionsEntities;
    }

    public void setOptionsEntities(List<OptionsEntity> optionsEntities) {
        this.optionsEntities = optionsEntities;
    }
}
