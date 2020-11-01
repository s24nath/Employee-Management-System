import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @FXML
    TextField idNum;
    @FXML
    PasswordField password;

    public void login(ActionEvent event) throws SQLException, IOException {

        String fetchSql = "SELECT Id,Password FROM account";
        ps = connection.prepareStatement(fetchSql);
        rs = ps.executeQuery();

        if(rs.next()) {
            if((rs.getString("Id").equals(idNum.getText())) && (rs.getString("Password").equals(password.getText()))) {

                connection.close();
                ps.close();
                rs.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent root = (Parent) loader.load();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
                stage.setScene(new Scene(root));
                stage.show();

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Input !");
                alert.setContentText("I.D. or Password didn't matched");
                alert.getButtonTypes().removeAll(ButtonType.OK,ButtonType.CANCEL);
                alert.getButtonTypes().addAll(ButtonType.CLOSE);
                alert.showAndWait();

            }
        }
    }

    public void forgot(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("forgotPassword.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void securityQues(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeSecurityQues.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void idChange(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("idChange.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

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
