package be.atc.LocacarJSF.validators;

import be.atc.LocacarJSF.beans.InsurancesBean;
import be.atc.LocacarJSF.dao.entities.AdsEntity;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * @author Younes - Arifi
 */
@FacesValidator("addBasketValidator")
public class AddBasketValidator implements Validator {

    public static Logger log = Logger.getLogger(InsurancesBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * Validator for Price
     *
     * @param context   FacesContext
     * @param component UIComponent
     * @param value     Object
     * @throws ValidatorException ValidatorException
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        LocalDateTime date = LocalDateTime.now();

        AdsEntity adsEntity = (AdsEntity) value;

        boolean test = (adsEntity.isActive()) && (adsEntity.getCarsByIdCars().isActive()) && (!adsEntity.getDateEnd().isBefore(date));

        log.info("Validator : ads active, car active, dateEnd !");

        if (!test) {
            log.error("Add Basket Error");
            throw new ValidatorException(new FacesMessage(getMessageError()));
        }

    }

    /**
     * Return message for exception
     *
     * @return String
     */
    private String getMessageError() {
        return JsfUtils.returnMessage(locale, "fxs.addShopButton.adsOrVehiculeError");
    }

}
