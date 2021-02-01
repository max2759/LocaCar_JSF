package be.atc.LocacarJSF.beans;

import org.apache.log4j.Logger;

import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Class abstract.
 */
public abstract class ExtendBean {

    public static Logger log = Logger.getLogger(InsurancesBean.class);

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private Date date = new Date();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
