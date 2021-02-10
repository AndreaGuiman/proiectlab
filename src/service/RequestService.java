package service;

import dao.RequestDao;
import model.Requests;

import javax.persistence.Persistence;
import java.util.List;

public class RequestService {
    private RequestDao requestDao;

    public RequestService(){
        try{
            requestDao = new RequestDao(Persistence.createEntityManagerFactory("proiectmip"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addRequest(Requests requests){
        requestDao.create(requests);
    }

    public List<Requests> getAllRequests(){
        return requestDao.findAll();
    }
}
