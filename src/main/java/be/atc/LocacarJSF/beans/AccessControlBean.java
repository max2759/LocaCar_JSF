package be.atc.LocacarJSF.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "accessControlBean")
@RequestScoped
public class AccessControlBean implements Serializable {
    private static final long serialVersionUID = 7486573412145610272L;

    private final FacesContext fc = FacesContext.getCurrentInstance();
    private final ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
    @Inject
    private UsersBean usersBean;
    @Inject
    private RolesPermissionsBean rolesPermissionsBean;

    public void isNotLogged() {
        if (!usersBean.isConnected()) {
            nav.performNavigation("/errorPages/accessDenied.xhtml");
        }
    }

    public void checkPermission(ComponentSystemEvent event) {
        if (usersBean.isConnected()) {
            String permission = (String) event.getComponent().getAttributes().get("permRequired");

            boolean value = rolesPermissionsBean.checkPermissionUserWithString(permission);

            if (!value) {
                hasNotPermission();
            }
        } else {
            isNotLogged();
        }
    }

    public void hasNotPermission() {
        nav.performNavigation("/errorPages/accessDenied.xhtml");

    }

}
