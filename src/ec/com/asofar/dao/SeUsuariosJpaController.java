/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.SeUsuarioSucurRol;
import ec.com.asofar.dto.SeUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class SeUsuariosJpaController implements Serializable {

    public SeUsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeUsuarios seUsuarios) {
        if (seUsuarios.getSeUsuarioSucurRolList() == null) {
            seUsuarios.setSeUsuarioSucurRolList(new ArrayList<SeUsuarioSucurRol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SePersonas idPersona = seUsuarios.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                seUsuarios.setIdPersona(idPersona);
            }
            List<SeUsuarioSucurRol> attachedSeUsuarioSucurRolList = new ArrayList<SeUsuarioSucurRol>();
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRolToAttach : seUsuarios.getSeUsuarioSucurRolList()) {
                seUsuarioSucurRolListSeUsuarioSucurRolToAttach = em.getReference(seUsuarioSucurRolListSeUsuarioSucurRolToAttach.getClass(), seUsuarioSucurRolListSeUsuarioSucurRolToAttach.getSeUsuarioSucurRolPK());
                attachedSeUsuarioSucurRolList.add(seUsuarioSucurRolListSeUsuarioSucurRolToAttach);
            }
            seUsuarios.setSeUsuarioSucurRolList(attachedSeUsuarioSucurRolList);
            em.persist(seUsuarios);
            if (idPersona != null) {
                idPersona.getSeUsuariosList().add(seUsuarios);
                idPersona = em.merge(idPersona);
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRol : seUsuarios.getSeUsuarioSucurRolList()) {
                SeUsuarios oldIdUsuarioOfSeUsuarioSucurRolListSeUsuarioSucurRol = seUsuarioSucurRolListSeUsuarioSucurRol.getIdUsuario();
                seUsuarioSucurRolListSeUsuarioSucurRol.setIdUsuario(seUsuarios);
                seUsuarioSucurRolListSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListSeUsuarioSucurRol);
                if (oldIdUsuarioOfSeUsuarioSucurRolListSeUsuarioSucurRol != null) {
                    oldIdUsuarioOfSeUsuarioSucurRolListSeUsuarioSucurRol.getSeUsuarioSucurRolList().remove(seUsuarioSucurRolListSeUsuarioSucurRol);
                    oldIdUsuarioOfSeUsuarioSucurRolListSeUsuarioSucurRol = em.merge(oldIdUsuarioOfSeUsuarioSucurRolListSeUsuarioSucurRol);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeUsuarios seUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeUsuarios persistentSeUsuarios = em.find(SeUsuarios.class, seUsuarios.getIdUsuario());
            SePersonas idPersonaOld = persistentSeUsuarios.getIdPersona();
            SePersonas idPersonaNew = seUsuarios.getIdPersona();
            List<SeUsuarioSucurRol> seUsuarioSucurRolListOld = persistentSeUsuarios.getSeUsuarioSucurRolList();
            List<SeUsuarioSucurRol> seUsuarioSucurRolListNew = seUsuarios.getSeUsuarioSucurRolList();
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                seUsuarios.setIdPersona(idPersonaNew);
            }
            List<SeUsuarioSucurRol> attachedSeUsuarioSucurRolListNew = new ArrayList<SeUsuarioSucurRol>();
            for (SeUsuarioSucurRol seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach : seUsuarioSucurRolListNew) {
                seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach = em.getReference(seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach.getClass(), seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach.getSeUsuarioSucurRolPK());
                attachedSeUsuarioSucurRolListNew.add(seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach);
            }
            seUsuarioSucurRolListNew = attachedSeUsuarioSucurRolListNew;
            seUsuarios.setSeUsuarioSucurRolList(seUsuarioSucurRolListNew);
            seUsuarios = em.merge(seUsuarios);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getSeUsuariosList().remove(seUsuarios);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getSeUsuariosList().add(seUsuarios);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListOldSeUsuarioSucurRol : seUsuarioSucurRolListOld) {
                if (!seUsuarioSucurRolListNew.contains(seUsuarioSucurRolListOldSeUsuarioSucurRol)) {
                    seUsuarioSucurRolListOldSeUsuarioSucurRol.setIdUsuario(null);
                    seUsuarioSucurRolListOldSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListOldSeUsuarioSucurRol);
                }
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListNewSeUsuarioSucurRol : seUsuarioSucurRolListNew) {
                if (!seUsuarioSucurRolListOld.contains(seUsuarioSucurRolListNewSeUsuarioSucurRol)) {
                    SeUsuarios oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol = seUsuarioSucurRolListNewSeUsuarioSucurRol.getIdUsuario();
                    seUsuarioSucurRolListNewSeUsuarioSucurRol.setIdUsuario(seUsuarios);
                    seUsuarioSucurRolListNewSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListNewSeUsuarioSucurRol);
                    if (oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol != null && !oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol.equals(seUsuarios)) {
                        oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol.getSeUsuarioSucurRolList().remove(seUsuarioSucurRolListNewSeUsuarioSucurRol);
                        oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol = em.merge(oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seUsuarios.getIdUsuario();
                if (findSeUsuarios(id) == null) {
                    throw new NonexistentEntityException("The seUsuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeUsuarios seUsuarios;
            try {
                seUsuarios = em.getReference(SeUsuarios.class, id);
                seUsuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seUsuarios with id " + id + " no longer exists.", enfe);
            }
            SePersonas idPersona = seUsuarios.getIdPersona();
            if (idPersona != null) {
                idPersona.getSeUsuariosList().remove(seUsuarios);
                idPersona = em.merge(idPersona);
            }
            List<SeUsuarioSucurRol> seUsuarioSucurRolList = seUsuarios.getSeUsuarioSucurRolList();
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRol : seUsuarioSucurRolList) {
                seUsuarioSucurRolListSeUsuarioSucurRol.setIdUsuario(null);
                seUsuarioSucurRolListSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListSeUsuarioSucurRol);
            }
            em.remove(seUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeUsuarios> findSeUsuariosEntities() {
        return findSeUsuariosEntities(true, -1, -1);
    }

    public List<SeUsuarios> findSeUsuariosEntities(int maxResults, int firstResult) {
        return findSeUsuariosEntities(false, maxResults, firstResult);
    }

    private List<SeUsuarios> findSeUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeUsuarios.class));
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

    public SeUsuarios findSeUsuarios(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeUsuarios> rt = cq.from(SeUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
