package be.atc.LocacarJSF.validators;

import be.atc.LocacarJSF.beans.PicturesBean;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import java.util.Locale;

/**
 * @author Maximilien - Zabbara
 */

@FacesValidator("carsPicturesNotRequiredValidator")
public class CarsPicturesNotRequiredValidator implements Validator {

    public static Logger log = Logger.getLogger(PicturesBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();


    /**
     * Validator for image size and format
     *
     * @param facesContext
     * @param uiComponent
     * @param value
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        if (value != null) {

            log.info("Début validation image");
            Part file = (Part) value;

            // get file size
            long fileSizeInBytes = file.getSize();
            log.info("Taille de l'image : " + fileSizeInBytes);
            // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
            long fileSizeInKB = fileSizeInBytes / 1024;
            // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
            long fileSizeInMB = fileSizeInKB / 1024;
            log.info("Taille de l'image en mb : " + fileSizeInMB);
            String ext = file.getContentType();
            log.info("Extension = " + ext);


            if (fileSizeInMB < 5) {
                if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
                    log.info("L'image n'est pas au bon format");
                    throw new ValidatorException(new FacesMessage(getMessageError()));
                }
            } else {
                log.info("L'image dépasse la limite autorisée");
                throw new ValidatorException(new FacesMessage(getMessageError()));
            }
        }


    }

    /**
     * Return message for size error
     *
     * @return
     */
    private String getMessageError() {
        return JsfUtils.returnMessage(locale, "carsPictures.uploadError");
    }

}
