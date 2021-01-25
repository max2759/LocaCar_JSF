package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.OptionsEntity;

import java.util.List;

public interface OptionsDAO {

    public boolean add(OptionsEntity optionsEntity);

    public boolean update(OptionsEntity optionsEntity);

    public List<OptionsEntity> findAll();

    public OptionsEntity findById(int id);
}
