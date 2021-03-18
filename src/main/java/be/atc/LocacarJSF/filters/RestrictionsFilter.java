package be.atc.LocacarJSF.filters;

import be.atc.LocacarJSF.beans.UsersBean;
import be.atc.LocacarJSF.dao.entities.UsersEntity;
import be.atc.LocacarJSF.services.UsersServices;
import be.atc.LocacarJSF.services.UsersServicesImpl;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class RestrictionsFilter implements Filter {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(RestrictionsFilter.class);

    private UsersEntity usersEntity;
    private final UsersServices usersServices = new UsersServicesImpl();
    private List<UsersEntity> usersEntities;

    @Inject
    private UsersBean usersBean;

    public static final String ACCES_PUBLIC     = "/index.xhtml";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    public void init( FilterConfig config ) throws ServletException {

    }

    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */

       // log.info(usersServices.findByUsername(usersEntity.getUsername()));
        log.info("beans : "+ usersBean.getUsersEntity().getUsername() );

        if ( usersBean.getUsersEntity() == null ) {
            /* Redirection vers la page publique */

            response.sendRedirect( request.getContextPath() + ACCES_PUBLIC );
        } else {
            /* Affichage de la page restreinte */
            log.info("recup user + son role dans le restric filter: "+usersBean.getUsersEntity().getRolesByIdRoles().getId());

            chain.doFilter( request, response );
        }
    }

    public void destroy() {
    }
}
