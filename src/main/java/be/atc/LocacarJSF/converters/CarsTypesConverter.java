package be.atc.LocacarJSF.converters;

import be.atc.LocacarJSF.dao.entities.CarsTypesEntity;
import be.atc.LocacarJSF.services.CarsTypesServices;
import be.atc.LocacarJSF.services.CarsTypesServicesImpl;
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

@FacesConverter("carTypesConverter")
public class CarsTypesConverter implements Converter {

    CarsTypesServices carsTypesServices = new CarsTypesServicesImpl();
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        CarsTypesEntity carsTypesEntity;

        if (value != null) {
            carsTypesEntity = carsTypesServices.findByLabel(value);
            return carsTypesEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.brandsConverter.error")));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }
}
