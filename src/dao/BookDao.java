package dao;

import model.Books;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BookDao extends GenericDao<Books> {
    private EntityManagerFactory factory;

    public BookDao(EntityManagerFactory factory){
        super(Books.class);
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
