package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;

/**
 * @author Younes - Arifi
 */
public interface ContractInsurancesDAO {

    boolean add(ContractInsurancesEntity contractInsurancesEntity);

    boolean update(ContractInsurancesEntity contractInsurancesEntity);

    boolean delete(int id);

    ContractInsurancesEntity findById(int id);

    ContractInsurancesEntity findByIdContract(int idContract);
}
