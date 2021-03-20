package be.atc.LocacarJSF.services;


import be.atc.LocacarJSF.dao.CitiesDAO;
import be.atc.LocacarJSF.dao.CitiesDAOImpl;
import be.atc.LocacarJSF.dao.entities.CitiesEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * @author Larche Marie-ellise
 */
public class CitiesServicesImpl implements CitiesServices {

    public static Logger log = Logger.getLogger(CitiesServicesImpl.class);

    CitiesDAO citiesDAO = new CitiesDAOImpl();

    @Override
    public boolean add(CitiesEntity citiesEntity) {
        if (citiesEntity != null) {
            return citiesDAO.add(citiesEntity);
        }
        return false;
    }

    @Override
    public boolean update(CitiesEntity citiesEntity) {
        if (citiesEntity != null && findById(citiesEntity.getId()) != null) {
            return citiesDAO.update(citiesEntity);

        }
        return false;
    }

    @Override
    public boolean delete(CitiesEntity citiesEntity) {
        return false;
    }

    @Override
    public List<CitiesEntity> findAll() {
        log.info("List<CitiesEntity> findAll() - begin");
        List<CitiesEntity> res = citiesDAO.findAll();
        if (res != null) {
            log.info("List<CitiesEntity> findAll() - end with " + res.size() + " result(s).");
        }
        return res;
    }

    @Override
    public CitiesEntity findById(int id) {
        if (id != 0) {
            return citiesDAO.findById(id);
        }
        return null;
    }

    @Override
    public List<CitiesEntity> findByLabel(String label) {
        if (label != null) {
            return citiesDAO.findByLabel(label);
        }
        return null;
    }

    @Override
    public List<CitiesEntity> findByIdUser(int idUser) {
        if (idUser != 0) {
            return citiesDAO.findByIdUser(idUser);
        }
        return Collections.emptyList();
    }

    @Override
    public CitiesEntity findByUser(int idUser) {
        if (idUser != 0) {
            return citiesDAO.findByUser(idUser);
        }
        return null;
    }
}
