package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.OptionsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface OptionsServices {
    public boolean add(OptionsEntity optionsEntity);

    public boolean update(OptionsEntity optionsEntity);

    public List<OptionsEntity> findAll();

    public OptionsEntity findById(int id);

    public List<OptionsEntity> findByLabel(String label);

}
