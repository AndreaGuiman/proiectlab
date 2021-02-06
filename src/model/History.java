package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class History {
    private int idHistory;
    private Integer idBook;
    private Integer idClient;

    @Id
    @Column(name = "idHistory")
    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
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
    @Column(name = "idClient")
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return idHistory == history.idHistory && Objects.equals(idBook, history.idBook) && Objects.equals(idClient, history.idClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistory, idBook, idClient);
    }
}
