package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Reviews {
    private int idReview;
    private String reviewText;
    private Integer idBook;
    private Integer idClient;
    private Integer bookRating;

    @Id
    @Column(name = "idReview")
    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    @Basic
    @Column(name = "reviewText")
    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
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

    @Basic
    @Column(name = "bookRating")
    public Integer getBookRating() {
        return bookRating;
    }

    public void setBookRating(Integer bookRating) {
        this.bookRating = bookRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviews reviews = (Reviews) o;
        return idReview == reviews.idReview && Objects.equals(reviewText, reviews.reviewText) && Objects.equals(idBook, reviews.idBook) && Objects.equals(idClient, reviews.idClient) && Objects.equals(bookRating, reviews.bookRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReview, reviewText, idBook, idClient, bookRating);
    }
}
