package be.atc.LocacarJSF.converters;

import be.atc.LocacarJSF.dao.entities.CitiesEntity;
import be.atc.LocacarJSF.services.CitiesServices;
import be.atc.LocacarJSF.services.CitiesServicesImpl;
import utils.JsfUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.Locale;

@FacesConverter("citiesConverter")
public class CitiesConverter implements Converter {

    CitiesServices citiesServices = new CitiesServicesImpl();
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();


    @Override
    public CitiesEntity getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        CitiesEntity citiesEntity;

        if (value != null) {
            citiesEntity = (CitiesEntity) citiesServices.findByLabel(value);
            return citiesEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.citiesConverter.error")));
        }


    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }
}
