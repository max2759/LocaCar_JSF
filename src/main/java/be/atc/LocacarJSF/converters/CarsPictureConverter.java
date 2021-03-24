package be.atc.LocacarJSF.converters;

import be.atc.LocacarJSF.beans.PicturesBean;
import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;
import be.atc.LocacarJSF.services.CarsPicturesServices;
import be.atc.LocacarJSF.services.CarsPicturesServicesImpl;
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
@FacesConverter("carsPicturesConverter")
public class CarsPictureConverter implements Converter {

    public static Logger log = Logger.getLogger(PicturesBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        log.info("Début picture converter");

        int idPictures = Integer.parseInt(value);

        CarsPicturesServices carsPicturesServices = new CarsPicturesServicesImpl();
        CarsPicturesEntity carsPicturesEntity = carsPicturesServices.findById(idPictures);

        if (carsPicturesEntity != null) {
            return carsPicturesEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.addShopButton.adsError")));
        }


    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }
}
