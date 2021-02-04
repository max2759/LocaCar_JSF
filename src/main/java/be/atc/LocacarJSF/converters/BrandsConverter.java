package be.atc.LocacarJSF.converters;

import be.atc.LocacarJSF.dao.entities.BrandsEntity;
import be.atc.LocacarJSF.services.BrandsServices;
import be.atc.LocacarJSF.services.BrandsServicesImpl;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.List;

@FacesConverter("brandsConverter")
public class BrandsConverter implements Converter<BrandsEntity> {

    BrandsServices brandsServices = new BrandsServicesImpl();
    List<BrandsEntity> brandsEntityList = brandsServices.findAll();
    boolean found = false;


    @Override
    public BrandsEntity getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        BrandsEntity brandsEntity = null;
        int brandsId = 0;

        try {
            brandsId = Integer.parseInt(value);
        } catch (NumberFormatException e) {

        }

        if (brandsId != 0) {
            for (BrandsEntity brands : brandsEntityList) {
                if (brands.getId() == brandsId) {
                    found = true;
                    brandsEntity = brands;
                    break;
                }
            }
            if (found) {
                return brandsEntity;
            } else {
                brandsEntity = null;
                throw new ConverterException();
            }
        }
        return brandsEntity;


    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, BrandsEntity brandsEntity) {
        if (brandsEntity != null) {
            return String.valueOf(brandsEntity.getId());
        }
        return null;


    }
}
