package be.atc.LocacarJSF.converters;


import be.atc.LocacarJSF.beans.CarsBean;
import be.atc.LocacarJSF.dao.entities.CarsEntity;
import be.atc.LocacarJSF.services.CarsServices;
import be.atc.LocacarJSF.services.CarsServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.Locale;

/**
 * @author Zabbara - Maximilien
 */

@FacesConverter(value = "carsConverter")
public class CarsConverter implements Converter {

    public static Logger log = Logger.getLogger(CarsBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        log.info("Cars converter");
        int idCars = Integer.parseInt(value);

        CarsServices carsServices = new CarsServicesImpl();
        CarsEntity carsEntity = carsServices.findById(idCars);

        if (carsEntity != null) {
            return carsEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.addShopButton.adsError")));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }
}
