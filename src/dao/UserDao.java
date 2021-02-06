package dao;

import model.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao extends GenericDao<Users>{
    private EntityManagerFactory factory;

    public UserDao(EntityManagerFactory factory){
        super(Users.class);
        this.factory = factory;
    }

    @Override
    public EntityManager getEntityManager() {
        try {
            return factory.createEntityManager();
        }catch (Exception e){
            System.out.println("The entity cannot be created!");
            return null;
        }
    }

    public Users findByUsername(String username){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Users> cq = cb.createQuery(Users.class);
        Root<Users> r = cq.from(Users.class);
        ParameterExpression<String> usernameParameter = cb.parameter(String.class);
        cq.select(r).where(cb.equal(r.get("username"), usernameParameter));
        TypedQuery<Users> query = em.createQuery(cq);
        query.setParameter(usernameParameter, username);
        List<Users> usersList = query.getResultList();
        if(usersList.size() > 0){
            return usersList.get(0);
        }
        else
            return null;
    }
}
