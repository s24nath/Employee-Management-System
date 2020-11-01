package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteView implements Initializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @FXML
    Label id;
    @FXML
    Label name;
    @FXML
    Label phone;
    @FXML
    Label email;
    @FXML
    Label adhar;
    @FXML
    Label dep;
    @FXML
    Label state;
    @FXML
    Label city;
    @FXML
    Label join;
    @FXML
    ImageView photo;
    @FXML
    Label gender;
    @FXML
    ScrollPane pane;

    public void delete(ActionEvent event) {

        try {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Record From Data Base");
            alert.setContentText("Are You Sure About Deleting This Record");
            alert.getButtonTypes().removeAll(ButtonType.OK);
            alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> choose = alert.showAndWait();

            if (choose.get() == ButtonType.YES) {

                String delete = "DELETE FROM employeerec WHERE Id=?";
                ps = connection.prepareStatement(delete);
                ps.setString(1, id.getText());
                int deleted = ps.executeUpdate();

                if(deleted > 0) {

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setContentText("Data Has Been Successfully Removed From DataBase");
                    alert.getButtonTypes().removeAll(ButtonType.OK, ButtonType.CANCEL);
                    alert.getButtonTypes().addAll(ButtonType.CLOSE);
                    alert.showAndWait();
                    connection.close();
                    ps.close();
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.close();
                }
                else {
                    System.out.println("Unable to Delete");
                }

            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setLabels(String l1, String l2, String l3, String l4, String l5, String l6, String l7, String l8, String l9, Image img, String l11) {

        id.setText(l1);
        name.setText(l2);
        phone.setText(l3);
        email.setText(l4);
        adhar.setText(l5);
        dep.setText(l6);
        state.setText(l7);
        city.setText(l8);
        join.setText(l9);
        photo.setImage(img);
        gender.setText(l11);
        pane.setVvalue(0);

    }

    public void cancel(ActionEvent event) throws SQLException {
        connection.close();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Data Base Driver Loaded");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/officeemployees", "root", "");
            System.out.println("Connection is Established");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
