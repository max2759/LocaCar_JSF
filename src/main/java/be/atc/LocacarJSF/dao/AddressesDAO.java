package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.AddressesEntity;

import java.util.List;

public interface AddressesDAO {
    //appels en DB
    public boolean add(AddressesEntity addressesEntity);

    public boolean update(AddressesEntity addressesEntity);

    public boolean delete(AddressesEntity addressesEntity);

    public List<AddressesEntity> findAll();

    public AddressesEntity findById(int id);

    public List<AddressesEntity> findByLabel(String label);

    public List<AddressesEntity> findByIdUser(int idUser);
}
