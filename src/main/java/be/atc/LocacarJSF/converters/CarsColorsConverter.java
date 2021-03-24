package be.atc.LocacarJSF.converters;

import be.atc.LocacarJSF.dao.entities.CarsColorsEntity;
import be.atc.LocacarJSF.services.CarsColorsServices;
import be.atc.LocacarJSF.services.CarsColorsServicesImpl;
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
@FacesConverter("carsColorsConverter")
public class CarsColorsConverter implements Converter {

    CarsColorsServices carsColorsServices = new CarsColorsServicesImpl();
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        CarsColorsEntity carsColorsEntity;

        if (value != null) {
            carsColorsEntity = carsColorsServices.findByLabel(value);
            return carsColorsEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.carsColorsConverter.error")));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }
}
