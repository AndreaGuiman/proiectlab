package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Managers {
    private int idManager;
    private String firstNameManager;
    private String lastNameManager;
    private String telephoneNumber;
    private String emailManager;


    @Id
    @Column(name = "idManager")
    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    @Basic
    @Column(name = "firstNameManager")
    public String getFirstNameManager() {
        return firstNameManager;
    }

    public void setFirstNameManager(String firstNameManager) {
        this.firstNameManager = firstNameManager;
    }

    @Basic
    @Column(name = "lastNameManager")
    public String getLastNameManager() {
        return lastNameManager;
    }

    public void setLastNameManager(String lastNameManager) {
        this.lastNameManager = lastNameManager;
    }

    @Basic
    @Column(name = "telephoneNumber")
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "emailManager")
    public String getEmailManager() {
        return emailManager;
    }

    public void setEmailManager(String emailManager) {
        this.emailManager = emailManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Managers managers = (Managers) o;
        return idManager == managers.idManager && Objects.equals(firstNameManager, managers.firstNameManager) && Objects.equals(lastNameManager, managers.lastNameManager) && Objects.equals(telephoneNumber, managers.telephoneNumber) && Objects.equals(emailManager, managers.emailManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idManager, firstNameManager, lastNameManager, telephoneNumber, emailManager);
    }
}
