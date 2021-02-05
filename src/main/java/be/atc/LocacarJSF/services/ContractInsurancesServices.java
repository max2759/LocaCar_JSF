package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;

/**
 * @author Younes - Arifi
 */
public interface ContractInsurancesServices {
    boolean add(ContractInsurancesEntity contractInsurancesEntity);

    boolean update(ContractInsurancesEntity contractInsurancesEntity);

    boolean delete(int id);

    ContractInsurancesEntity findById(int id);

    ContractInsurancesEntity findByIdContract(int idContract);
}
