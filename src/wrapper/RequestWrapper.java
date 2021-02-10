package wrapper;

import model.Books;
import model.Clients;
import model.Requests;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RequestWrapper {
//    private List<Clients> clientsList;
//    private List<Books> booksList;
    private String firstNameClient;
    private String lastNameClient;
    private String emailClient;
    private String bookName;
    private Date startDate;
    private Date endDate;

    public RequestWrapper(Clients clients, Books books, Requests requests){
        this.firstNameClient = clients.getFirstNameClient();
        this.lastNameClient = clients.getLastNameClient();
        this.emailClient = clients.getEmailClient();
        this.bookName = books.getBookName();
        this.startDate = requests.getStartDate();
        this.endDate = requests.getEndDate();
    }

    public String getFirstNameClient() {
        return firstNameClient;
    }

    public void setFirstNameClient(String firstNameClient) {
        this.firstNameClient = firstNameClient;
    }

    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
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
        return "RequestWrapper{" +
                "firstNameClient='" + firstNameClient + '\'' +
                ", lastNameClient='" + lastNameClient + '\'' +
                ", emailClient='" + emailClient + '\'' +
                ", bookName='" + bookName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
