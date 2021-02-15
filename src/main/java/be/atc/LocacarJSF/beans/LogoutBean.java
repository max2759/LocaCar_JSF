package be.atc.LocacarJSF.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named(value = "logoutBean")
@SessionScoped
public class LogoutBean implements Serializable {
    private static final long serialVersionUID = -553644905745421591L;


    /**
     * Login test
     */
    public void loginTest(ActionEvent e) {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(true)).invalidate();
    }
}
