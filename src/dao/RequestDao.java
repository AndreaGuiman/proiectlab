package dao;

import model.Books;
import model.Requests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class RequestDao extends GenericDao<Requests> {
    private EntityManagerFactory factory;

    public RequestDao(EntityManagerFactory factory){
        super(Requests.class);
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
}
