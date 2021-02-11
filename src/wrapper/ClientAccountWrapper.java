package wrapper;

import model.Books;
import model.Requests;

import java.sql.Date;

public class ClientAccountWrapper {
    private String authorName;
    private String bookName;
    private Date startDate;
    private Date endDate;

    public ClientAccountWrapper(Books books, Requests requests){
        this.authorName = books.getAuthorName();
        this.bookName = books.getBookName();
        this.startDate = requests.getStartDate();
        this.endDate = requests.getEndDate();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ClientAccountWrapper{" +
                "authorName='" + authorName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
