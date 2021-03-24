package be.atc.LocacarJSF.dao;


import be.atc.LocacarJSF.dao.entities.AddressesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public class AddressesDAOImpl implements AddressesDAO {

    public static Logger log = Logger.getLogger(AddressesDAOImpl.class);

    @Override
    public boolean add(AddressesEntity addressesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(addressesEntity);
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
    public boolean update(AddressesEntity addressesEntity) {
        log.info("begin udate in DAO");
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            log.info("begin try");
            tx = em.getTransaction();
            tx.begin();
            em.merge(addressesEntity);
            tx.commit();
            log.info("Merge ok");
            return true;
        } catch (Exception ex) {
            log.info("begin catch");
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Merge echec");
            return false;
        } finally {
            log.info("begin finaly");
            em.clear();
            em.close();
        }

    }

    @Override
    public boolean delete(AddressesEntity addressesEntity) {
        return false;
    }

    @Override
    public List<AddressesEntity> findAll() {
        EntityManager em = EMF.getEM();
        List<AddressesEntity> resultList = null;
        log.info("cvall to findAll DAO");
        try {
            resultList = em.createNamedQuery("Addresses.findAll",
                    AddressesEntity.class)
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
    public AddressesEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(AddressesEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AddressesEntity> findByLabel(String label) {
        return null;
    }

    @Override
    public AddressesEntity findByIdUser(int idUser) {
        EntityManager em = EMF.getEM();
        //   List<AddressesEntity> resultList = null;
        log.info("cvall to findAudiuser DAO");
        try {
            return em.createNamedQuery("Adresses.findByIdUser",
                    AddressesEntity.class)
                    .setParameter("idUser", idUser)
                    .getSingleResult();
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
    public List<AddressesEntity> findAllAddressesByUserId(int idUser) {
        EntityManager em = EMF.getEM();
        List<AddressesEntity> resultList = null;
        log.info("cvall to findAll DAO");
        try {
            resultList = em.createNamedQuery("Adresses.findByIdUser",
                    AddressesEntity.class)
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

   /* @Override
    public List<AddressesEntity> findByLabel(String label) {
        EntityManager em = EMF.getEM();

        log.info("addresse in DAO : " + label);

        try {
            return em.createNamedQuery("addresses.findByLabel",
                    AddressesEntity.class)
                    .setParameter("label", label)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }*/


}
