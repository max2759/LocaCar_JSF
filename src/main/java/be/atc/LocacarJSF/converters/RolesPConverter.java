package be.atc.LocacarJSF.converters;


import be.atc.LocacarJSF.dao.entities.RolesEntity;
import be.atc.LocacarJSF.services.RolesServices;
import be.atc.LocacarJSF.services.RolesServicesImpl;
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
@FacesConverter(value = "rolesPConverter")
public class RolesPConverter implements Converter {
    public static Logger log = Logger.getLogger(RolesPConverter.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        log.info("RolesPermissions converter");
        int idRolesP = Integer.parseInt(value);

        RolesServices rolesServices = new RolesServicesImpl();
        RolesEntity rolesEntity = rolesServices.findById(idRolesP);

        if (rolesEntity != null) {
            return rolesEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.addButton.adsError")));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }
}
