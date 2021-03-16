package be.atc.LocacarJSF.converters;

import be.atc.LocacarJSF.dao.entities.BrandsEntity;
import be.atc.LocacarJSF.services.BrandsServices;
import be.atc.LocacarJSF.services.BrandsServicesImpl;
import utils.JsfUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.Locale;

/**
 * @author Maximilien - Zabbara
 * Brands Converter
 */
@FacesConverter("brandsConverter")
public class BrandsConverter implements Converter {

    BrandsServices brandsServices = new BrandsServicesImpl();
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();


    @Override
    public BrandsEntity getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        BrandsEntity brandsEntity;

        if (value == null || value.isEmpty()) {
            return null; // Let required="true" do its job on this.
        }

        if (value != null) {
            brandsEntity = brandsServices.findByLabel(value);
            return brandsEntity;
        } else {
            throw new ConverterException(new FacesMessage(JsfUtils.returnMessage(locale, "fxs.brandsConverter.error")));
        }


    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return null;
    }
}
