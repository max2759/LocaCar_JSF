package be.atc.LocacarJSF.dao;


import be.atc.LocacarJSF.dao.entities.CitiesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public class CitiesDAOImpl implements CitiesDAO {

    public static Logger log = Logger.getLogger(CitiesDAOImpl.class);

    @Override
    public boolean add(CitiesEntity citiesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(citiesEntity);
            tx.commit();
            log.info("Persist ok");
            return true;
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Persist echec");
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public boolean update(CitiesEntity citiesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(citiesEntity);
            tx.commit();
            log.info("Merge ok");
            return true;
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Merge echec");
            return false;
        } finally {
            em.clear();
            em.close();
        }

    }

    @Override
    public boolean delete(CitiesEntity citiesEntity) {
        return false;
    }

    @Override
    public List<CitiesEntity> findAll() {
        EntityManager em = EMF.getEM();
        List<CitiesEntity> resultList = null;
        log.info("cvall to findAll DAO");
        try {
            resultList = em.createNamedQuery("Cities.findAll",
                    CitiesEntity.class)
                    .getResultList();
            if (resultList != null) {
                log.info(resultList.size() + " results found.");
            }
        } catch (Exception ex) {
            log.error(ex);
        } finally {
            em.clear();
            em.close();
        }
        return resultList;
    }

    @Override
    public CitiesEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(CitiesEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<CitiesEntity> findByLabel(String label) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Cities.findByLabel",
                    CitiesEntity.class)
                    .setParameter("label", label)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }

    }

    @Override
    public List<CitiesEntity> findByIdUser(int idUser) {
        EntityManager em = EMF.getEM();
        //   List<CitiesEntity> resultList = null;
        log.info("cvall to findAudiuser DAO");
        try {
            return em.createNamedQuery("Cities.findByUser",
                    CitiesEntity.class)
                    .setParameter("idUser", idUser)
                    .getResultList();
           /* if (!resultList.isEmpty()) {
                log.info(resultList.size() + " results found.");
            }*/
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
        // return resultList;
    }

    @Override
    public CitiesEntity findByUser(int idUser) {
        EntityManager em = EMF.getEM();
        //   List<AddressesEntity> resultList = null;
        log.info("cvall to findAudiuser DAO");
        try {
            return em.createNamedQuery("Cities.findByUser",
                    CitiesEntity.class)
                    .setParameter("idUser", idUser)
                    .getSingleResult();
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }


}
