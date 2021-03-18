package be.atc.LocacarJSF.validators;

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
 * @author Younes - Arifi
 */
@FacesValidator("leasingTimeValidator")
public class LeasingTimeValidator implements Validator {

    public static Logger log = Logger.getLogger(LeasingTimeValidator.class);
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
        int i = (int) value;

        if ((i != 36) && (i != 48) && (i != 60)) {
            log.error("Leasing Time Error");
            throw new ValidatorException(new FacesMessage(getMessageError()));
        }

    }

    /**
     * Return message for exception
     *
     * @return
     */
    private String getMessageError() {
        return JsfUtils.returnMessage(locale, "fxs.basket.leasingTimeError");
    }
}
