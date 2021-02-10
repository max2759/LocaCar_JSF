package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("picturesBean")
@RequestScoped
public class PicturesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -7193767475781021472L;

    private CarsPicturesEntity carsPicturesEntity;

    public CarsPicturesEntity getCarsPicturesEntity() {
        return carsPicturesEntity;
    }

    public void setCarsPicturesEntity(CarsPicturesEntity carsPicturesEntity) {
        this.carsPicturesEntity = carsPicturesEntity;
    }
}
