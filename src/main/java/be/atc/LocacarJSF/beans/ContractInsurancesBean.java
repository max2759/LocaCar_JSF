package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import be.atc.LocacarJSF.services.ContractInsurancesServices;
import be.atc.LocacarJSF.services.ContractInsurancesServicesImpl;
import utils.JsfUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import static java.lang.Integer.parseInt;

/**
 * @author Younes - Arifi
 */
@Named(value = "contractInsurancesBean")
@SessionScoped
public class ContractInsurancesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 2613097507996434366L;

    ContractInsurancesServices contractInsurancesServices = new ContractInsurancesServicesImpl();
    ContractInsurancesEntity contractInsurancesEntity;

    private boolean showPopup;

    @Inject
    private ContractsBean contractsBean;
    @Inject
    private OrdersBean ordersBean;
    @Inject
    private InsurancesBean insurancesBean;

    /**
     * Initialisation fields
     */
    public void initialisationFields() {
        log.info("ContractInsurancesBean : initialisationFields");
    }

    /**
     * Open popup when edit
     */
    public void showPopupModal() {
        log.info("ContractInsurancesBean : showPopupModal");
        showPopup = true;
        if (getParam("idContract") != null) {
            int idContract = parseInt(getParam("idContract"));
            contractInsurancesEntity = contractInsurancesServices.findByIdContract(idContract);
        }
    }

    /**
     * Close popup
     */
    public void hidePopupModal() {
        log.info("ContractInsurancesBean : hidePopupModal");
        initialisationFields();
        showPopup = false;
    }

    /**
     * Repetition code for update entity
     */
    protected boolean functionUpdateEntity() {
        log.info("ContractInsurancesBean : functionUpdateEntity");
        contractInsurancesEntity.setInsurancesByIdInsurance(insurancesBean.getInsurancesEntity());
        contractInsurancesEntity.setInsurancePrice(insurancesBean.getInsurancesEntity().getPrice());
        return contractInsurancesServices.update(contractInsurancesEntity);
    }

    /**
     * check and save entity
     */
    public void saveEdit() {
        log.info("ContractInsurancesBean : saveEdit");
        FacesContext context = FacesContext.getCurrentInstance();

        boolean test = functionUpdateEntity();
        if (test) {
            test = contractsBean.updateContract(contractInsurancesEntity.getContractsByIdContract());
        }
        if (test) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "successUpdate"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorUpdate"), null));
        }
        ordersBean.init();
    }

    /**
     * Create new Entity
     *
     * @param insurancesEntity InsurancesEntity
     * @return boolean
     */
    protected boolean createContractInsurances(InsurancesEntity insurancesEntity) {
        log.info("ContractInsurancesBean : createContractInsurances");

        contractInsurancesEntity = new ContractInsurancesEntity();
        contractInsurancesEntity.setContractsByIdContract(contractsBean.getContractsEntity());
        contractInsurancesEntity.setInsurancesByIdInsurance(insurancesEntity);
        contractInsurancesEntity.setInsurancePrice(insurancesEntity.getPrice());

        return contractInsurancesServices.add(contractInsurancesEntity);
    }

    /**
     * Find contract insurance by idContract
     *
     * @param idContract type int
     * @return ContractInsurancesEntity
     */
    protected ContractInsurancesEntity findContractInsurancesByIdContract(int idContract) {
        log.info("ContractInsurancesBean : findContractInsurancesByIdContract");
        return contractInsurancesServices.findByIdContract(idContract);
    }

    /**
     * Delete contract insurance for leasing
     *
     * @param contractsEntity ContractsEntity
     * @return boolean
     */
    protected boolean deleteContractInsurance(ContractsEntity contractsEntity) {
        log.info("ContractInsurancesBean : deleteContractInsurance");
        contractInsurancesEntity = contractInsurancesServices.findByIdContract(contractsEntity.getId());
        return contractInsurancesEntity != null && contractInsurancesServices.delete(contractInsurancesEntity.getId());
    }

    public ContractInsurancesEntity getContractInsurancesEntity() {
        return contractInsurancesEntity;
    }

    public void setContractInsurancesEntity(ContractInsurancesEntity contractInsurancesEntity) {
        this.contractInsurancesEntity = contractInsurancesEntity;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }
}

