package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Books {
    private int idBook;
    private String bookName;
    private String bookGenre;
    private Integer bookRating;
    private Integer idAuthor;
    private Integer stoc;

    @Id
    @Column(name = "idBook")
    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    @Basic
    @Column(name = "bookName")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "bookGenre")
    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    @Basic
    @Column(name = "bookRating")
    public Integer getBookRating() {
        return bookRating;
    }

    public void setBookRating(Integer bookRating) {
        this.bookRating = bookRating;
    }

    @Basic
    @Column(name = "idAuthor")
    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Basic
    @Column(name = "stoc")
    public Integer getStoc() {
        return stoc;
    }

    public void setStoc(Integer stoc) {
        this.stoc = stoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return idBook == books.idBook && Objects.equals(bookName, books.bookName) && Objects.equals(bookGenre, books.bookGenre) && Objects.equals(bookRating, books.bookRating) && Objects.equals(idAuthor, books.idAuthor) && Objects.equals(stoc, books.stoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, bookName, bookGenre, bookRating, idAuthor, stoc);
    }

    @Override
    public String toString() {
        return "Books{" +
                "idBook=" + idBook +
                ", bookName='" + bookName + '\'' +
                ", bookGenre='" + bookGenre + '\'' +
                ", bookRating=" + bookRating +
                ", idAuthor=" + idAuthor +
                ", stoc=" + stoc +
                '}';
    }
}
