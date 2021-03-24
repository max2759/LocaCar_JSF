package be.atc.LocacarJSF.converters;

import be.atc.LocacarJSF.dao.entities.CarsGearboxEntity;
import be.atc.LocacarJSF.services.CarsGearboxServices;
import be.atc.LocacarJSF.services.CarsGearboxServicesImpl;
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

@FacesConverter("carsGearboxConverter")
public class CarsGearboxConverter implements Converter {

    CarsGearboxServices carsGearboxServices = new CarsGearboxServicesImpl();
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        CarsGearboxEntity carsGearboxEntity;

        if (value != null) {
            carsGearboxEntity = carsGearboxServices.findByLabel(value);
            return carsGearboxEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.carsGearboxConverter.error")));
        }

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }
}
