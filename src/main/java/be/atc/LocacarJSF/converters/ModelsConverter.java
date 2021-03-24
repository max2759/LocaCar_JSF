package be.atc.LocacarJSF.converters;


import be.atc.LocacarJSF.dao.entities.ModelsEntity;
import be.atc.LocacarJSF.services.ModelsServices;
import be.atc.LocacarJSF.services.ModelsServicesImpl;
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

@FacesConverter("modelsConverter")
public class ModelsConverter implements Converter {

    ModelsServices modelsServices = new ModelsServicesImpl();
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        ModelsEntity modelsEntity;
        if (value != null) {
            modelsEntity = modelsServices.findByLabelEntity(value);
            return modelsEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.modelsConverter.error")));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }
}
