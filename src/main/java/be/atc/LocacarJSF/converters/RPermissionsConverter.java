package be.atc.LocacarJSF.converters;


import be.atc.LocacarJSF.dao.entities.PermissionsEntity;
import be.atc.LocacarJSF.services.PermissionsServices;
import be.atc.LocacarJSF.services.PermissionsServicesImpl;
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
 * @author Larche Marie-Élise
 */
@FacesConverter(value = "rPermissionsConverter")
public class RPermissionsConverter implements Converter {

    public static Logger log = Logger.getLogger(RPermissionsConverter.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        log.info("RolesPermissions converter");

        int idRPermissions = Integer.parseInt(value);
        PermissionsServices permissionsServices = new PermissionsServicesImpl();
        PermissionsEntity permissionsEntity = permissionsServices.findById(idRPermissions);

        if (permissionsEntity != null) {
            return permissionsEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.addButton.adsError")));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }

}
