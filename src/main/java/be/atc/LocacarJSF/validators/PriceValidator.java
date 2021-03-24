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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Younes - Arifi
 */
@FacesValidator("priceValidator")
public class PriceValidator implements Validator {

    public static Logger log = Logger.getLogger(PriceValidator.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * Validator for Price
     *
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Pattern pattern = Pattern.compile("(\\d+\\.\\d{1,2})");
        String string = value.toString();
        Matcher match = pattern.matcher(string);
        log.info("Validator price");

        if (!match.matches()) {
            log.error("Price Error");
            throw new ValidatorException(new FacesMessage(getMessageErrorPrice()));
        }

    }

    /**
     * Message for exception
     *
     * @return error message
     */
    private String getMessageErrorPrice() {
        return JsfUtils.returnMessage(locale, "fxs.insurancesList.priceError");
    }
}
