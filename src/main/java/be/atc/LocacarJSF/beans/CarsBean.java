package be.atc.LocacarJSF.beans;


import be.atc.LocacarJSF.services.CarsServices;
import be.atc.LocacarJSF.services.CarsServicesImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "carsBean")
@RequestScoped
public class CarsBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -293903106522268390L;

    CarsServices carsServices = new CarsServicesImpl();


}
