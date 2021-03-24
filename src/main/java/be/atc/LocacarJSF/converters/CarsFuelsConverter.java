package be.atc.LocacarJSF.converters;

import be.atc.LocacarJSF.dao.entities.CarsFuelsEntity;
import be.atc.LocacarJSF.services.CarsFuelsServices;
import be.atc.LocacarJSF.services.CarsFuelsServicesImpl;
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
@FacesConverter("carsFuelsConverter")
public class CarsFuelsConverter implements Converter {

    CarsFuelsServices carsFuelsServices = new CarsFuelsServicesImpl();
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        CarsFuelsEntity carsFuelsEntity;
        if (value != null) {
            carsFuelsEntity = carsFuelsServices.findByLabel(value);
            return carsFuelsEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.carsFuelsConverter.error")));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }
}
