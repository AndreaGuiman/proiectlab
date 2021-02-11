package service;

import dao.ReviewDao;
import model.Reviews;

import javax.persistence.Persistence;

public class ReviewService {
    private ReviewDao reviewDao;

    public ReviewService(){
        try{
            reviewDao = new ReviewDao(Persistence.createEntityManagerFactory("proiectmip"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addReview(Reviews reviews){
        reviewDao.create(reviews);
    }
}
