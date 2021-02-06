package service;

import dao.UserDao;
import model.Users;

import javax.persistence.Persistence;
import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService(){
        try{
            userDao = new UserDao(Persistence.createEntityManagerFactory("proiectmip"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addUser(Users users){
        userDao.create(users);
    }

    public Users findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public List<Users> getAllUsers(){
        return userDao.findAll();
    }
}
