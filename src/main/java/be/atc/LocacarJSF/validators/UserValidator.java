package be.atc.LocacarJSF.validators;


import be.atc.LocacarJSF.beans.UsersBean;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Locale;

/**
 * @author Larche Marie-ellise
 */
@FacesValidator("UserValidator")
public class UserValidator implements Validator {

    public static Logger log = Logger.getLogger(UsersBean.class);
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

        Object otherValue = component.getAttributes().get("otherValue");

        if (value == null || otherValue == null) {
            return; // Let required="true" handle.
        }

        if (!value.equals(otherValue)) {
            throw new ValidatorException(new FacesMessage("Values are not equal."));
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
