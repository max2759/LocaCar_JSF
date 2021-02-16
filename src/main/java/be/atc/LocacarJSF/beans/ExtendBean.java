package be.atc.LocacarJSF.beans;

import org.apache.log4j.Logger;

import javax.faces.context.FacesContext;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

/**
 * @author Younes - Arifi
 * Class abstract.
 */
public abstract class ExtendBean {

    public static Logger log = Logger.getLogger(ExtendBean.class);

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private LocalDateTime date = LocalDateTime.now();
    /**
     * Méthode pour retourner les paramètres récupéré
     *
     * @param name Param form
     * @return param(name)
     */
    public String getParam(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(name);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
