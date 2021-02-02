package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;

public interface ContractInsurancesDAO {

    public boolean add(ContractInsurancesEntity contractInsurancesEntity);

    public boolean update(ContractInsurancesEntity contractInsurancesEntity);

    public boolean delete(int id);

    public ContractInsurancesEntity findById(int id);

}
