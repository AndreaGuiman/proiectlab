package dao;

import model.Clients;
import model.Managers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class ManagerDao extends GenericDao<Managers>{
    private EntityManagerFactory factory;

    public ManagerDao(EntityManagerFactory factory){
        super(Managers.class);
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

    public Managers findById(int id) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Managers> cq = cb.createQuery(model.Managers.class);
        Root<Managers> r = cq.from(model.Managers.class);
        ParameterExpression<Integer> idd = cb.parameter(int.class);
        cq.select(r).where(cb.equal(r.get("idManager"), idd));
        TypedQuery<Managers> query = em.createQuery(cq);
        query.setParameter(idd, id);
        return query.getResultList().get(0);
    }
}
