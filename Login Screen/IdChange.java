package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class IdChange implements Initializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String oldId;
    String password;

    @FXML
    TextField oldIdField;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField newIdField;

    public void change(ActionEvent event) throws SQLException {

        if(oldIdField.getText().equals(oldId) && passwordField.getText().equals(password)) {

            String sqlUpdate = "UPDATE account SET Id=? Where Id = ?";
            ps = connection.prepareStatement(sqlUpdate);

            ps.setString(1,newIdField.getText());
            ps.setString(2,oldId);
            int executed = ps.executeUpdate();

            if(executed > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("I.D. Has Been Successfully Changed");
                alert.getButtonTypes().removeAll(ButtonType.OK,ButtonType.CANCEL);
                alert.getButtonTypes().addAll(ButtonType.CLOSE);
                alert.showAndWait();
                connection.close();
                ps.close();
                rs.close();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Cannot Change I.D., Input Didn't Matched");
            alert.getButtonTypes().removeAll(ButtonType.OK,ButtonType.CANCEL);
            alert.getButtonTypes().addAll(ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

    public void cancel(ActionEvent event) throws SQLException {

        connection.close();
        ps.close();
        rs.close();

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

            String fetchSql = "SELECT Id,Password FROM account";
            ps = connection.prepareStatement(fetchSql);
            rs = ps.executeQuery();

            if(rs.next()) {
                oldId = rs.getString("Id");
                password = rs.getString("Password");
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
}
