package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.ContractTypesEntity;
import be.atc.LocacarJSF.services.ContractTypesServices;
import be.atc.LocacarJSF.services.ContractTypesServicesImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * @author Younes - Arifi
 * contractTypesBean
 */
@Named(value = "contractTypesBean")
@ApplicationScoped
public class ContractTypesBean extends ExtendBean {
    ContractTypesServices contractTypesServices = new ContractTypesServicesImpl();

    protected ContractTypesEntity findContractTypesById(int idContractTypes) {
        return contractTypesServices.findById(idContractTypes);
    }
}
