package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.AddressesEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface AddressesDAO {
    //appels en DB
    boolean add(AddressesEntity addressesEntity);

    boolean update(AddressesEntity addressesEntity);

    boolean delete(AddressesEntity addressesEntity);

    List<AddressesEntity> findAll();

    AddressesEntity findById(int id);

    List<AddressesEntity> findByLabel(String label);

    AddressesEntity findByIdUser(int idUser);

    List<AddressesEntity> findAllAddressesByUserId(int idUser);
}
