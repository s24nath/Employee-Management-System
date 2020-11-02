package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {

    public void add(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("addEmployee.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void tableView(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("emptable.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void viewEdit(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchViewEmp.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void delete(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchForDelete.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void exit(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginscreen.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();

    }

}
