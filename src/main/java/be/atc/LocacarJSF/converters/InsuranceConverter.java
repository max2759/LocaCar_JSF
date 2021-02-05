package be.atc.LocacarJSF.converters;

import be.atc.LocacarJSF.beans.InsurancesBean;
import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import be.atc.LocacarJSF.services.InsurancesServices;
import be.atc.LocacarJSF.services.InsurancesServicesImpl;
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
 * Insurance Converter
 */
@FacesConverter(value = "insuranceConverter")
public class InsuranceConverter implements Converter {

    public static Logger log = Logger.getLogger(InsurancesBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        log.info("Insurance Converter !");
        int idInsurance = Integer.parseInt(value);
        InsurancesServices insurancesServices = new InsurancesServicesImpl();
        InsurancesEntity insurancesEntity = insurancesServices.findById(idInsurance);

        if (insurancesEntity != null) {
            return insurancesEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.addShopButton.insuranceError")));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return null;
    }
}
