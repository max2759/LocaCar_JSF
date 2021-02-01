package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.dao.entities.OrdersEntity;
import be.atc.LocacarJSF.enums.EnumOrderStatut;
import be.atc.LocacarJSF.enums.EnumTypeAds;
import be.atc.LocacarJSF.services.AdsServices;
import be.atc.LocacarJSF.services.AdsServicesImpl;
import be.atc.LocacarJSF.services.OrdersServices;
import be.atc.LocacarJSF.services.OrdersServicesImpl;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import static java.lang.Integer.parseInt;

@Named(value = "ordersBean")
@ViewScoped
public class OrdersBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -5251107202124824837L;

    // Remplacer par l'utilisateur
    int idUser = 3;

    // Remplacer par le prix final
    double finalPrice = 32300;

    // Remplacer par le type d'assurance
    int idAssurance;


    private AdsEntity adsEntity;
    private AdsServices adsServices = new AdsServicesImpl();
    private OrdersEntity ordersEntity;
    private OrdersServices ordersServices = new OrdersServicesImpl();

    @Inject
    private ContractsBean contractsBean;

    /**
     * Add shop, call functions
     */
    public void addShop() {
        log.info("Add Shop ! idAds : " + getParam("idAds"));
        idAssurance = parseInt(getParam("idAssurance"));
        log.info("Add Shop ! idAssurance : " + idAssurance);

        adsEntity = adsServices.findById(parseInt(getParam("idAds")));

        // Check if ads is active, car is active, and dateEnd is not less than today. If true : find Orders By Id Users and Status is Pending, if null, create order. Else use the order
        ordersEntity = checkAdsActiveCarActiveDateEnd() == true ? findOrders_ByIdUsers_andStatusIsPending() : null;

        if (ordersEntity == null) {
            log.info("Aucun Orders n'est trouvé");
            createOrders();
        }

        String testSaleOrLeasing = checkIfSaleOrLeasing();
        log.info("Order est : " + testSaleOrLeasing);

        contractsBean.createContract(ordersEntity.getId(), adsEntity, finalPrice, null, testSaleOrLeasing, idAssurance);
    }

    /**
     * Check if : ads is active, car is active and dateEnd is not less than today.
     *
     * @return true or false
     */
    protected boolean checkAdsActiveCarActiveDateEnd() {
        log.info("Check : Ads is Active, Car is active and DateEnd not before Today ?");
        return (adsEntity.isActive() == true) && (adsEntity.getCarsByIdCars().isActive() == true) && (!adsEntity.getDateEnd().before(getDate())) ? true : false;
    }

    /**
     * Find orders By IdUsers and status is pending
     *
     * @return OrdersEntity or null
     */
    protected OrdersEntity findOrders_ByIdUsers_andStatusIsPending() {
        log.info(("Recherche d'Order, par Id Users et Orders.Status == Pending"));
        return ordersServices.findByIdUsersAndStatusIsPending(idUser);
    }

    /**
     * Check if Type ads is : Sale or Leasing
     *
     * @return
     */
    protected String checkIfSaleOrLeasing() {
        if (adsEntity.getTypeAds() == EnumTypeAds.Sale) {
            return "Sale";
        } else {
            return "Leasing";
        }
    }

    /**
     * Create Order !
     */
    protected void createOrders() {
        ordersEntity = new OrdersEntity();
        ordersEntity.setIdUsers(idUser);
        ordersEntity.setOrderDate(getDate());
        ordersEntity.setOrderStatut(EnumOrderStatut.Pending);
        ordersServices.add(ordersEntity);
    }


}
