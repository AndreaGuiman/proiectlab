package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Users {
    private int idUser;
    private Integer idManager;
    private Integer idClient;
    private String username;
    private String password;

    public Users(Integer idClient, String username, String password) {
        this.idClient = idClient;
        this.username = username;
        this.password = password;
    }

    public Users() {

    }

    @Id
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "idManager")
    public Integer getIdManager() {
        return idManager;
    }

    public void setIdManager(Integer idManager) {
        this.idManager = idManager;
    }

    @Basic
    @Column(name = "idClient")
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return idUser == users.idUser && Objects.equals(idManager, users.idManager) && Objects.equals(idClient, users.idClient) && Objects.equals(username, users.username) && Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idManager, idClient, username, password);
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUser=" + idUser +
                ", idManager=" + idManager +
                ", idClient=" + idClient +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
