package be.atc.LocacarJSF.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@SessionScoped
public class InternalizationBean implements Serializable {
    private static final long serialVersionUID = 3233925319240020562L;

    public void langChoice(ActionEvent actionEvent) {
        Locale locale;
        String idComponent = actionEvent.getComponent().getId();

        locale = new Locale(idComponent);

        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
