package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
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

    public void initialisationFields() {
        success = "";
        fail = "";
    }

    /**
     * Open popup when edit
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("idContract") != null) {
            int idContract = parseInt(getParam("idContract"));
            contractInsurancesEntity = contractInsurancesServices.findByIdContract(idContract);
        }
        log.info("Contract Insurances Entity : " + contractInsurancesEntity.getId());
        log.info("Contract Insurances Entity Insurance : " + contractInsurancesEntity.getInsurancesByIdInsurance().getLabel());
    }

    /**
     * Repetition code for update entity
     */
    public void functionUpdateEntity() {
        log.info("Update entity");
        contractInsurancesEntity.setInsurancesByIdInsurance(insurancesBean.getInsurancesEntity());
        contractInsurancesEntity.setInsurancePrice(insurancesBean.getInsurancesEntity().getPrice());
        contractInsurancesServices.update(contractInsurancesEntity);
        success = JsfUtils.returnMessage(getLocale(), "successUpdate");
    }

    /**
     * check and save entity
     */
    public void saveEdit() {
        log.info("Contract Insurances Entity : " + contractInsurancesEntity.getId());
        log.info("Contract Insurances Entity Insurance : " + contractInsurancesEntity.getInsurancesByIdInsurance().getLabel());
        functionUpdateEntity();

        contractsBean.updateContract(contractInsurancesEntity.getContractsByIdContract());
        ordersBean.init();
    }

    /**
     * Close popup
     */
    public void hidePopupModal() {
        log.info("Hide PopupModal ");
        initialisationFields();
        showPopup = false;
    }

    protected boolean createContractInsurances(InsurancesEntity insurancesEntity) {
        log.info("Create new Contract Assurance for Leasing !");

        contractInsurancesEntity = new ContractInsurancesEntity();

        contractInsurancesEntity.setContractsByIdContract(contractsBean.getContractsEntity());
        contractInsurancesEntity.setInsurancesByIdInsurance(insurancesEntity);
        contractInsurancesEntity.setInsurancePrice(insurancesEntity.getPrice());

        return contractInsurancesServices.add(contractInsurancesEntity);
    }

    protected ContractInsurancesEntity findContractInsurancesByIdContract(int idContract) {
        return contractInsurancesServices.findByIdContract(idContract);
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

