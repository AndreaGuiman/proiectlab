package service;

import dao.ClientDao;
import model.Clients;

import javax.persistence.Persistence;
import java.util.List;

public class ClientService {
    private ClientDao clientDao;

    public ClientService(){
        try{
            clientDao = new ClientDao(Persistence.createEntityManagerFactory("proiectmip"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addClient(Clients clients){
        clientDao.create(clients);
    }
    public List<Clients> getAllClients(){
        return clientDao.findAll();
    }

    public Clients findByFirstName(String firstName){
        return clientDao.findByFirstName(firstName);
    }
}
