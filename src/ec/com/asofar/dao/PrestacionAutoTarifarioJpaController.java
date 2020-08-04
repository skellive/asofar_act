/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dto.PrestacionAutoTarifario;
import ec.com.asofar.dto.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author User
 */
public class PrestacionAutoTarifarioJpaController implements Serializable {

    public PrestacionAutoTarifarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrestacionAutoTarifario prestacionAutoTarifario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(prestacionAutoTarifario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrestacionAutoTarifario prestacionAutoTarifario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            prestacionAutoTarifario = em.merge(prestacionAutoTarifario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = prestacionAutoTarifario.getIdTipoPrestaciones();
                if (findPrestacionAutoTarifario(id) == null) {
                    throw new NonexistentEntityException("The prestacionAutoTarifario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrestacionAutoTarifario prestacionAutoTarifario;
            try {
                prestacionAutoTarifario = em.getReference(PrestacionAutoTarifario.class, id);
                prestacionAutoTarifario.getIdTipoPrestaciones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestacionAutoTarifario with id " + id + " no longer exists.", enfe);
            }
            em.remove(prestacionAutoTarifario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrestacionAutoTarifario> findPrestacionAutoTarifarioEntities() {
        return findPrestacionAutoTarifarioEntities(true, -1, -1);
    }

    public List<PrestacionAutoTarifario> findPrestacionAutoTarifarioEntities(int maxResults, int firstResult) {
        return findPrestacionAutoTarifarioEntities(false, maxResults, firstResult);
    }

    private List<PrestacionAutoTarifario> findPrestacionAutoTarifarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrestacionAutoTarifario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PrestacionAutoTarifario findPrestacionAutoTarifario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrestacionAutoTarifario.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrestacionAutoTarifarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrestacionAutoTarifario> rt = cq.from(PrestacionAutoTarifario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
