package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CitiesEntity;

import java.util.List;

public interface CitiesServices {
    //fait les verifs (genre pas nul...)
    public boolean add(CitiesEntity citiesEntity);

    public boolean update(CitiesEntity citiesEntity);

    public boolean delete(CitiesEntity citiesEntity);

    public List<CitiesEntity> findAll();

    public CitiesEntity findById(int id);

    public List<CitiesEntity> findByLabel(String label);

    public List<CitiesEntity> findByIdUser(int idUser);

    public CitiesEntity findByUser(int idUser);
}
