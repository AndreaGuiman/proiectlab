package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Clients {
    private int idClient;
    private String firstNameClient;
    private String lastNameClient;
    private String telephoneNumber;
    private String emailClient;

    public Clients() {
    }

    public Clients(String firstNameClient, String lastNameClient, String telephoneNumber, String emailClient) {
        this.firstNameClient = firstNameClient;
        this.lastNameClient = lastNameClient;
        this.telephoneNumber = telephoneNumber;
        this.emailClient = emailClient;
    }

    @Id
    @Column(name = "idClient")
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Basic
    @Column(name = "firstNameClient")
    public String getFirstNameClient() {
        return firstNameClient;
    }

    public void setFirstNameClient(String firstNameClient) {
        this.firstNameClient = firstNameClient;
    }

    @Basic
    @Column(name = "lastNameClient")
    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
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
    @Column(name = "emailClient")
    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return idClient == clients.idClient && Objects.equals(firstNameClient, clients.firstNameClient) && Objects.equals(lastNameClient, clients.lastNameClient) && Objects.equals(telephoneNumber, clients.telephoneNumber) && Objects.equals(emailClient, clients.emailClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, firstNameClient, lastNameClient, telephoneNumber, emailClient);
    }

    @Override
    public String toString() {
        return "Clients{" +
                "idClient=" + idClient +
                ", firstNameClient='" + firstNameClient + '\'' +
                ", lastNameClient='" + lastNameClient + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", emailClient='" + emailClient + '\'' +
                '}';
    }
}
