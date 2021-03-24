package be.atc.LocacarJSF.services;


import be.atc.LocacarJSF.dao.AddressesDAO;
import be.atc.LocacarJSF.dao.AddressesDAOImpl;
import be.atc.LocacarJSF.dao.entities.AddressesEntity;
import org.apache.log4j.Logger;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public class AddressesServicesImpl implements AddressesServices {

    public static Logger log = Logger.getLogger(AddressesServicesImpl.class);

    AddressesDAO addressesDAO = new AddressesDAOImpl();

    @Override
    public boolean add(AddressesEntity addressesEntity) {
        if (addressesEntity != null) {
            return addressesDAO.add(addressesEntity);
        }
        return false;
    }

    @Override
    public boolean update(AddressesEntity addressesEntity) {
        if (addressesEntity != null && findById(addressesEntity.getId()) != null) {
            log.info("begn update in service");
            return addressesDAO.update(addressesEntity);

        }
        return false;
    }

    @Override
    public boolean delete(AddressesEntity addressesEntity) {
        return false;
    }

    @Override
    public List<AddressesEntity> findAll() {

        log.info("findall iuserServ");
        return addressesDAO.findAll();
    }

    @Override
    public AddressesEntity findById(int id) {
        if (id != 0) {
            return addressesDAO.findById(id);
        }
        return null;
    }

    @Override
    public List<AddressesEntity> findByLabel(String label) {
        if (label != null) {
            return addressesDAO.findByLabel(label);
        }
        return null;
    }

    @Override
    public AddressesEntity findByIdUser(int idUser) {
        if (idUser != 0) {
            return addressesDAO.findByIdUser(idUser);
        }
        return null;
    }

    @Override
    public List<AddressesEntity> findAllAddressesByUserId(int idUser) {
        if (idUser != 0) {
            return addressesDAO.findAllAddressesByUserId(idUser);
        }
        return null;
    }
}
