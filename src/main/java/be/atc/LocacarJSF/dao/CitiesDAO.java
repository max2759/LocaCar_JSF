package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CitiesEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface CitiesDAO {
    //appels en DB
    boolean add(CitiesEntity citiesEntity);

    boolean update(CitiesEntity citiesEntity);

    boolean delete(CitiesEntity citiesEntity);

    List<CitiesEntity> findAll();

    CitiesEntity findById(int id);

    List<CitiesEntity> findByLabel(String label);

    List<CitiesEntity> findByIdUser(int idUser);

    CitiesEntity findByUser(int idUser);
}
