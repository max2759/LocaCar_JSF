package be.atc.LocacarJSF.validators;

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
 * @author Maximilien - Zabbara
 */
@FacesValidator("dateValidator")
public class DateValidator implements Validator {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        LocalDateTime dateNow = LocalDateTime.now();

        LocalDateTime valueDate = (LocalDateTime) value;

        if (value != null) {
            if (valueDate.isAfter(dateNow)) {
                throw new ValidatorException(new FacesMessage(getMessageErrorDate()));
            }
        }
    }

    /**
     * Return message for exception
     *
     * @return String
     */
    private String getMessageErrorDate() {
        return JsfUtils.returnMessage(locale, "fxs.dateError");
    }
}
