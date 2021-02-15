package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import be.atc.LocacarJSF.services.ContractInsurancesServices;
import be.atc.LocacarJSF.services.ContractInsurancesServicesImpl;
import utils.JsfUtils;

import javax.enterprise.context.SessionScoped;
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

    String success;
    String fail;
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
        success = "";
        fail = "";
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
        boolean test = functionUpdateEntity();
        if (test == true) {
            test = contractsBean.updateContract(contractInsurancesEntity.getContractsByIdContract());
        }
        if (test == true) {
            fail = "";
            success = JsfUtils.returnMessage(getLocale(), "successUpdate");
        } else {
            success = "";
            fail = JsfUtils.returnMessage(getLocale(), "errorUpdate");
        }
        ordersBean.init();
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
     * Create new Entity
     *
     * @param insurancesEntity
     * @return
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
     * @param idContract
     * @return
     */
    protected ContractInsurancesEntity findContractInsurancesByIdContract(int idContract) {
        log.info("ContractInsurancesBean : findContractInsurancesByIdContract");
        return contractInsurancesServices.findByIdContract(idContract);
    }

    /**
     * Delete contract insurance for leasing
     *
     * @param contractsEntity
     * @return
     */
    protected boolean deleteContractInsurance(ContractsEntity contractsEntity) {
        log.info("ContractInsurancesBean : deleteContractInsurance");
        contractInsurancesEntity = contractInsurancesServices.findByIdContract(contractsEntity.getId());
        return contractInsurancesEntity != null ? contractInsurancesServices.delete(contractInsurancesEntity.getId()) : false;
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

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }
}

