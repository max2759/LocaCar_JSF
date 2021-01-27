package be.atc.LocacarJSF.validators;

import be.atc.LocacarJSF.beans.InsurancesBean;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("priceValidator")
public class PriceValidator implements Validator {

    public static Logger log = Logger.getLogger(InsurancesBean.class);

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

    private String getMessageErrorPrice() {
        String message = "Ne peut contenir que des chiffres";
        return message;
    }
}
