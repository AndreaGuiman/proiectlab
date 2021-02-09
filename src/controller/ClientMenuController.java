package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Books;
import model.Clients;
import model.Users;
import service.BookService;
import service.ClientService;
import service.UserService;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientMenuController {
    @FXML
    Button buttonFilter;
    @FXML
    Hyperlink hyperlinkClientAccount, hyperlinkLogOut;
    @FXML
    Label username;
    @FXML
    TableView<Books> tableBooks;
    @FXML
    TableColumn<Books, String> bookNameColumn, bookGenreColumn, bookAuthorColumn;
    @FXML
    TableColumn<Books, Integer> bookRatingColumn, bookStockColumn;
    @FXML
    ChoiceBox<String> authorSelection, genreSelection;

    public void initialize() throws Exception {
        Clients clients = getClient();
        username.setText(clients.getFirstNameClient() + " " + clients.getLastNameClient());

        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        bookGenreColumn.setCellValueFactory(new PropertyValueFactory<>("bookGenre"));
        bookRatingColumn.setCellValueFactory(new PropertyValueFactory<>("bookRating"));
        bookStockColumn.setCellValueFactory(new PropertyValueFactory<>("stoc"));

        setDefaultTableItems();

        BookService bookService = new BookService();
        List<Books> booksList = bookService.getAllBooks();
        List<String> authorNameList = new ArrayList<>();
        List<String> bookGenreList = new ArrayList<>();
//        authorNameList.add("");
//        bookGenreList.add("");
        for(Books booksIterator: booksList) {
            if (!authorNameList.contains(booksIterator.getAuthorName()))
                authorNameList.add(booksIterator.getAuthorName());
            if(!bookGenreList.contains(booksIterator.getBookGenre()))
                bookGenreList.add(booksIterator.getBookGenre());
        }
        try{
            if(authorNameList.size() != 0){
                authorSelection.setItems(FXCollections.observableArrayList(authorNameList));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            if(bookGenreList.size() != 0){
                genreSelection.setItems(FXCollections.observableArrayList(bookGenreList));
            }
        }catch (Exception e){
            e.printStackTrace();
        }



//        sortByRating();
//        sortByRatingReverse();
    }

    public void setDefaultTableItems(){
        ObservableList<Books> booksObservableList = FXCollections.observableArrayList(getAllBooks());
        tableBooks.setItems(booksObservableList);
    }

    public void setTableItems(ObservableList<Books> booksObservableList){
        tableBooks.setItems(booksObservableList);
    }

    public void filterSearch(){
        boolean authorSelected = false, genreSelected = false;
        BookService bookService = new BookService();
        List<Books> booksList = bookService.getAllBooks();
        ObservableList<Books> booksObservableList = FXCollections.observableArrayList();
        // cand avem o optiune selectata in choice box
        if(authorSelection.getSelectionModel().getSelectedItem() != null){
            authorSelected = true;
        }
        if(genreSelection.getSelectionModel().getSelectedItem() != null){
            genreSelected = true;
        }

        if(authorSelected && genreSelected){
            for(Books booksIterator: booksList) {
                if (booksIterator.getAuthorName().equals(authorSelection.getSelectionModel().getSelectedItem())
                        && booksIterator.getBookGenre().equals(genreSelection.getSelectionModel().getSelectedItem()))
                    booksObservableList.add(booksIterator);
//                if(authorSelection.getSelectionModel().getSelectedItem().equals("")){
//                    if(booksIterator.getBookGenre().equals(genreSelection.getSelectionModel().getSelectedItem())){
//                        booksObservableList.add(booksIterator);
//                    }
//                }
//                if(genreSelection.getSelectionModel().getSelectedItem().equals("")){
//                    if(booksIterator.getBookGenre().equals(authorSelection.getSelectionModel().getSelectedItem())){
//                        booksObservableList.add(booksIterator);
//                    }
//                }
//                if (authorSelection.getSelectionModel().getSelectedItem().equals("") &&
//                genreSelection.getSelectionModel().getSelectedItem().equals(""))
//                {
//                    setDefaultTableItems();
//                    booksObservableList.add(null);
//
//                }
//            }
            }
            if(booksObservableList.size() == 0){
                setDefaultTableItems();
                throw new IllegalStateException("Nu s-au gasit carti in baza de date!");
            }
            else {
                setTableItems(booksObservableList);
            }
        }

        if(authorSelected && !genreSelected){
            for(Books booksIterator:booksList){
                if(booksIterator.getAuthorName().equals(authorSelection.getSelectionModel().getSelectedItem())){
                    booksObservableList.add(booksIterator);
                }
            }
            if(booksObservableList.size() == 0){
                setDefaultTableItems();
                throw new IllegalStateException("Nu s-au gasit carti in baza de date!");
            }
            else {
                setTableItems(booksObservableList);
            }
        }

        if(!authorSelected && genreSelected){
            for(Books booksIterator:booksList){
                if(booksIterator.getAuthorName().equals(genreSelection.getSelectionModel().getSelectedItem())){
                    booksObservableList.add(booksIterator);
                }
            }
            if(booksObservableList.size() == 0){
                setDefaultTableItems();
                throw new IllegalStateException("Nu s-au gasit carti in baza de date!");
            }
            else {
                setTableItems(booksObservableList);
            }
        }
        if(!authorSelected && !genreSelected){
            setDefaultTableItems();
            throw new IllegalStateException("Nu au fost completate campurile de filtrare!");
        }


//        System.out.println(booksObservableList);
//        String authorName = authorSelection.getSelectionModel().getSelectedItem();
//        System.out.println(authorName);
    }


    public List<Books> getAllBooks(){
        BookService bookService = new BookService();
        return bookService.getAllBooks();
    }


    public void openBookOption() throws IOException {
        if(tableBooks.getSelectionModel().getSelectedItem() != null) {
            try(PrintWriter writer = new PrintWriter(("src/session/BookSession.txt"))){
                writer.println(tableBooks.getSelectionModel().getSelectedItem().getBookName());
                //writer.println(password.getText());
            }catch (Exception e) {
                e.printStackTrace();
            }
            String bookName = tableBooks.getSelectionModel().getSelectedItem().getBookName();
            System.out.println(tableBooks.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("../scene/BookOption.fxml"));
            Stage bookOption = new Stage();
            bookOption.setScene(new Scene(root));
            bookOption.setTitle(bookName);
            bookOption.show();
        }
    }


    public void goToAccountDetails() throws IOException {
        hyperlinkClientAccount.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ClientAccount.fxml"));
        Stage accountDetails = new Stage();
        accountDetails.setScene(new Scene(root));
        accountDetails.setTitle("ACCOUNT DETAILS");
        accountDetails.show();
    }

    public void logOutAction() throws IOException {
        hyperlinkLogOut.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/Login.fxml"));
        Stage login = new Stage();
        login.setScene(new Scene(root));
        login.setTitle("LOGIN");
        login.show();
    }

    //sortare carti, dupa ratingul cartilor
    //ascendent
    public void sortByRating(){
        BookService bookService = new BookService();
        List<Books> booksList = bookService.getAllBooks();
        System.out.println(booksList);
        Collections.sort(booksList);
        System.out.println(booksList);
    }

    //descendent
    public void sortByRatingReverse() {
        BookService bookService = new BookService();
        List<Books> booksList = bookService.getAllBooks();
        System.out.println(booksList);
        Collections.sort(booksList);
        Collections.reverse(booksList);
        System.out.println(booksList);
    }

    public String getUsernameClient(){
        String username = null;
        try(BufferedReader br = new BufferedReader(new FileReader("src/session/Session.txt"))){
            String usernameTmp;
            while((usernameTmp = br.readLine()) != null)
                username = usernameTmp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return username;
    }

    public Clients getClient() throws Exception {
        String usernameTmp = getUsernameClient();
        UserService userService = new UserService();
        Users users = userService.findByUsername(usernameTmp);
        ClientService clientService = new ClientService();
        return clientService.findById(users.getIdClient());
    }
}
