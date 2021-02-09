package service;

import dao.ManagerDao;
import model.Managers;

import javax.persistence.Persistence;

public class ManagerService {
    private ManagerDao managerDao;

    public ManagerService(){
        try{
            managerDao = new ManagerDao(Persistence.createEntityManagerFactory("proiectmip"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Managers findById(int id){
        return managerDao.findById(id);
    }
}
