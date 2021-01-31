package be.atc.LocacarJSF.beans;

import org.apache.log4j.Logger;

import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.Map;

/**
 * Class abstract.
 */
public abstract class ExtendBean {

    public static Logger log = Logger.getLogger(InsurancesBean.class);

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

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
}
