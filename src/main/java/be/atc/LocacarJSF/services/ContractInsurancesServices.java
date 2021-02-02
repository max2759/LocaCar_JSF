package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;

public interface ContractInsurancesServices {
    public boolean add(ContractInsurancesEntity contractInsurancesEntity);

    public boolean update(ContractInsurancesEntity contractInsurancesEntity);

    public boolean delete(int id);

    public ContractInsurancesEntity findById(int id);
}
