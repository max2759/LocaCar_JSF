package be.atc.LocacarJSF.converters;

import be.atc.LocacarJSF.beans.InsurancesBean;
import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.services.AdsServices;
import be.atc.LocacarJSF.services.AdsServicesImpl;
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
 * @author Younes - Arifi
 * Ads Converter
 */
@FacesConverter(value = "adsConverter")
public class AdsConverter implements Converter {

    public static Logger log = Logger.getLogger(InsurancesBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        log.info("Ads Converter !");
        int idAds = Integer.parseInt(value);
        AdsServices adsServices = new AdsServicesImpl();
        AdsEntity adsEntity = adsServices.findById(idAds);

        if (adsEntity != null) {
            return adsEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.addShopButton.adsError")));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return null;
    }
}
