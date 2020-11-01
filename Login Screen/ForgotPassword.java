package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ForgotPassword implements Initializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String q1 = "What is your pet name ?";
    String q2 = "Where is your birth place ?";
    String q3 = "What is your secret hobby you haven't revealed yet ?";
    String q4 = "What is your favourite hobby ?";
    String q5 = "What is your chilhood name ?";
    String q6 = "What is your elementary school name";
    String q7 = "What is your favourite cartoon ?";
    String q8 = "What is your favourite comic ?";
    String q9 = "What is your favourite super hero ?";
    String q10 = "Any secret name or code ?";

    @FXML
    TextField id;
    @FXML
    PasswordField newpassword;
    @FXML
    PasswordField newpassword2;
    @FXML
    ComboBox<String> ques;
    @FXML
    TextField ans;

    ObservableList<String> questions = FXCollections.observableArrayList(q1,q2,q3,q4,q5,q6,q7,q8,q9,q10);

    String quesFromDb;
    String ansFromDb;
    String idFromDb;

    public void change(ActionEvent event) throws SQLException {

        if((quesFromDb.equals(ques.getValue())) && (ansFromDb.equalsIgnoreCase(ans.getText().trim())) && (idFromDb.trim().equals(id.getText().trim())) && reEnterPassword()) {

            String sqlUpdate = "UPDATE account SET Password=? Where Id = ?";
            ps = connection.prepareStatement(sqlUpdate);

            ps.setString(1,newpassword.getText());
            ps.setString(2,idFromDb);
            int executed = ps.executeUpdate();

            if(executed > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Password Has Been Successfully Changed");
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
            alert.setContentText("Cannot Change Password, Input Didn't Matched");
            alert.getButtonTypes().removeAll(ButtonType.OK,ButtonType.CANCEL);
            alert.getButtonTypes().addAll(ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    public boolean reEnterPassword() {
        return newpassword.getText().equals(newpassword2.getText());
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

        ques.setItems(questions);

        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Data Base Driver Loaded");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/officeemployees", "root", "");
            System.out.println("Connection is Established");

            String fetchSql = "SELECT Id,Question,Answer FROM account";
            ps = connection.prepareStatement(fetchSql);
            rs = ps.executeQuery();

            if(rs.next()) {
                quesFromDb = rs.getString("Question");
                ansFromDb = rs.getString("Answer");
                idFromDb = rs.getString("Id");
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
