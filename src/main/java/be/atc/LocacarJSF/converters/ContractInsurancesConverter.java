package be.atc.LocacarJSF.converters;

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
import java.util.List;
import java.util.Locale;

/**
 * @author Younes - Arifi
 * Ads Converter
 */
@FacesConverter(value = "contractInsurancesConverter")
public class ContractInsurancesConverter implements Converter {

    public static Logger log = Logger.getLogger(ContractInsurancesConverter.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        log.info("ContractInsurances Converter !");
        log.info("value : " + value);

        InsurancesServices insurancesServices = new InsurancesServicesImpl();
        List<InsurancesEntity> insurancesEntities = insurancesServices.findByLabel(value);

        if (!insurancesEntities.isEmpty()) {
            return insurancesEntities.get(0);
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.addShopButton.contractError")));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return null;
    }
}
