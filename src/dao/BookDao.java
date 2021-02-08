package dao;


import model.Books;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
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
    public Books findByBookName(String bookName){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Books> cq = cb.createQuery(Books.class);
        Root<Books> r = cq.from(Books.class);
        ParameterExpression<String> bookNameParameter = cb.parameter(String.class);
        cq.select(r).where(cb.equal(r.get("bookName"), bookNameParameter));
        TypedQuery<Books> query = em.createQuery(cq);
        query.setParameter(bookNameParameter, bookName);
        List<Books> booksList = query.getResultList();
        if(booksList.size() > 0){
            return booksList.get(0);
        }
        else
            return null;
    }
}
