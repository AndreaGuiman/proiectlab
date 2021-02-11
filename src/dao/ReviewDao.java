package dao;

import model.Reviews;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ReviewDao extends GenericDao<Reviews> {
    private EntityManagerFactory factory;

    public ReviewDao(EntityManagerFactory factory) {
        super(Reviews.class);
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

