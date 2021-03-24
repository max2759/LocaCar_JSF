package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ModelsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface ModelsServices {

    public boolean add(ModelsEntity modelsEntity);

    public boolean update(ModelsEntity modelsEntity);

    public List<ModelsEntity> findAll();

    public ModelsEntity findById(int id);

    public List<ModelsEntity> findByLabel(String label);

    public ModelsEntity findByLabelEntity(String label);

    List<ModelsEntity> findModelsByBrandsId(int brandsID);
}
