package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CitiesEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface CitiesServices {
    //fait les verifs (genre pas nul...)
    boolean add(CitiesEntity citiesEntity);

    boolean update(CitiesEntity citiesEntity);

    boolean delete(CitiesEntity citiesEntity);

    List<CitiesEntity> findAll();

    CitiesEntity findById(int id);

    List<CitiesEntity> findByLabel(String label);

    List<CitiesEntity> findByIdUser(int idUser);

    CitiesEntity findByUser(int idUser);
}
