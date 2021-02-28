package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.AddressesEntity;

import java.util.List;

public interface AddressesServices {
    //fait les verifs (genre pas nul...)
    public boolean add(AddressesEntity addressesEntity);

    public boolean update(AddressesEntity addressesEntity);

    public boolean delete(AddressesEntity addressesEntity);

    public List<AddressesEntity> findAll();

    public AddressesEntity findById(int id);

    public List<AddressesEntity> findByLabel(String label);

    public List<AddressesEntity> findByIdUser(int idUser);
}
