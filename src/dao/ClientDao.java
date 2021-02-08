package dao;


import model.Clients;
import model.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientDao extends GenericDao<Clients> {
    private EntityManagerFactory factory;

    public ClientDao(EntityManagerFactory factory){
        super(Clients.class);
        this.factory = factory;
    }

    @Override
    public EntityManager getEntityManager() {
        try {
            return factory.createEntityManager();
        } catch (Exception e) {
            System.out.println("The entity cannot be created!");
            return null;
        }
    }

    public List<Clients> findById(int id) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<model.Clients> cq = cb.createQuery(model.Clients.class);
        Root<model.Clients> r = cq.from(model.Clients.class);
        ParameterExpression<Integer> idd = cb.parameter(int.class);
        cq.select(r).where(cb.equal(r.get("idClient"), idd));
        TypedQuery<model.Clients> query = em.createQuery(cq);
        query.setParameter(idd, id);
        return query.getResultList();
    }

    public Clients findByFirstName(String firstName){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Clients> cq = cb.createQuery(Clients.class);
        Root<Clients> r = cq.from(Clients.class);
        ParameterExpression<String> firstNameParameter = cb.parameter(String.class);
        cq.select(r).where(cb.equal(r.get("firstNameClient"), firstNameParameter));
        TypedQuery<Clients> query = em.createQuery(cq);
        query.setParameter(firstNameParameter, firstName);
        List<Clients> clientsList = query.getResultList();
        if(clientsList.size() > 0){
            return clientsList.get(0);
        }
        else
            return null;
    }
}