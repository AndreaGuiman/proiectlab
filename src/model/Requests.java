package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Requests {
    private int idRequest;
    private Integer idClient;
    private Integer idBook;
    private Date startDate;
    private Date endDate;

    @Id
    @Column(name = "idRequest")
    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
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
    @Column(name = "idBook")
    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    @Basic
    @Column(name = "startDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "endDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requests requests = (Requests) o;
        return idRequest == requests.idRequest && Objects.equals(idClient, requests.idClient) && Objects.equals(idBook, requests.idBook) && Objects.equals(startDate, requests.startDate) && Objects.equals(endDate, requests.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRequest, idClient, idBook, startDate, endDate);
    }
}
