package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Authors {
    private int idAuthor;
    private String authorName;

    @Id
    @Column(name = "idAuthor")
    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Basic
    @Column(name = "authorName")
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authors authors = (Authors) o;
        return idAuthor == authors.idAuthor && Objects.equals(authorName, authors.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAuthor, authorName);
    }
}
