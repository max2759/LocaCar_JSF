package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.ModelsDAO;
import be.atc.LocacarJSF.dao.ModelsDAOImpl;
import be.atc.LocacarJSF.dao.entities.ModelsEntity;

import java.util.Collections;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class ModelsServicesImpl implements ModelsServices {

    ModelsDAO modelsDAO = new ModelsDAOImpl();

    @Override
    public boolean add(ModelsEntity modelsEntity) {
        if (modelsEntity != null) {
            return modelsDAO.add(modelsEntity);
        }
        return false;
    }

    @Override
    public boolean update(ModelsEntity modelsEntity) {
        if (modelsEntity != null && findById(modelsEntity.getId()) != null) {
            modelsDAO.update(modelsEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<ModelsEntity> findAll() {
        return modelsDAO.findAll();
    }

    @Override
    public ModelsEntity findById(int id) {
        if (id != 0) {
            return modelsDAO.findById(id);
        }
        return null;
    }

    @Override
    public List<ModelsEntity> findByLabel(String label) {
        if (label != null) {
            return modelsDAO.findByLabel(label);
        }
        return null;
    }

    @Override
    public ModelsEntity findByLabelEntity(String label) {
        if (label != null) {
            return modelsDAO.findByLabelEntity(label);
        }
        return null;
    }

    @Override
    public List<ModelsEntity> findModelsByBrandsId(int brandsID) {
        if (brandsID != 0) {
            return modelsDAO.findModelsByBrandsId(brandsID);
        }
        return Collections.emptyList();
    }
}
