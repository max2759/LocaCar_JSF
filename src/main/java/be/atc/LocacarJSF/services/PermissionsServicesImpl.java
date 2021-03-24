package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.PermissionsDAO;
import be.atc.LocacarJSF.dao.PermissionsDAOImpl;
import be.atc.LocacarJSF.dao.entities.PermissionsEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public class PermissionsServicesImpl implements PermissionsServices {

    PermissionsDAO permissionsDAO = new PermissionsDAOImpl();

    @Override
    public boolean add(PermissionsEntity permissionsEntity) {
        if (permissionsEntity != null) {
            return permissionsDAO.add(permissionsEntity);
        }
        return false;
    }

    @Override
    public boolean update(PermissionsEntity permissionsEntity) {
        if (permissionsEntity != null && findById(permissionsEntity.getId()) != null) {
            return permissionsDAO.update(permissionsEntity);

        }
        return false;
    }

    @Override
    public boolean delete(PermissionsEntity permissionsEntity) {
        return false;
    }

    @Override
    public List<PermissionsEntity> findAll() {
        return permissionsDAO.findAll();
    }

    @Override
    public PermissionsEntity findById(int id) {
        if (id != 0) {
            return permissionsDAO.findById(id);
        }
        return null;
    }

}
