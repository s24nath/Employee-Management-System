package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ChangeSecurityQues implements Initializable {


    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String q1 = "What is your pet name ?";
    String q2 = "Where is your birth place ?";
    String q3 = "What is your secret hobby you haven't revealed yet ?";
    String q4 = "What is your favourite hobby ?";
    String q5 = "What is your childhood name ?";
    String q6 = "What is your elementary school name";
    String q7 = "What is your favourite cartoon ?";
    String q8 = "What is your favourite comic ?";
    String q9 = "Which is your favourite super hero ?";
    String q10 = "Any secret name or code ?";
    ObservableList<String> questions = FXCollections.observableArrayList(q1,q2,q3,q4,q5,q6,q7,q8,q9,q10);

    @FXML
    TextField idNum;
    @FXML
    PasswordField password;
    @FXML
    ComboBox<String> ques;
    @FXML
    TextField ans;



    public void change(ActionEvent event) throws SQLException {

        String fetchSql = "SELECT * FROM account";
        ps = connection.prepareStatement(fetchSql);
        rs = ps.executeQuery();

        if(rs.next()) {
            if ((rs.getString("Id").trim().equals(idNum.getText().trim())) && (rs.getString("Password").equals(password.getText()))) {

                String sqlUpdate = "UPDATE account SET Question=?, Answer=? Where Id = ?";
                ps = connection.prepareStatement(sqlUpdate);
                ps.setString(1,ques.getValue());
                ps.setString(2,ans.getText().trim());
                ps.setString(3,(rs.getString("Id")));
                int executed = ps.executeUpdate();

                if(executed > 0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setContentText("Security Queastions Has Been Successfully Updated");
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
                alert.setContentText("Input Didn't Matched");
                alert.getButtonTypes().removeAll(ButtonType.OK,ButtonType.CANCEL);
                alert.getButtonTypes().addAll(ButtonType.CLOSE);
                alert.showAndWait();
            }
        }
    }

    public void cancel(ActionEvent event) throws SQLException {
        connection.close();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ques.setItems(questions);

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
