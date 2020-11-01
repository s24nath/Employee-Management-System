package sample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddEmp implements Initializable {

    FileChooser fileChooser = new FileChooser();
    File file;
    FileInputStream fis;
    @FXML
    TextField id;
    @FXML
    TextField name;
    @FXML
    TextField phone;
    @FXML
    TextField email;
    @FXML
    TextField adhaar;
    @FXML
    TextField dep;
    @FXML
    TextField state;
    @FXML
    TextField city;
    @FXML
    DatePicker join;
    @FXML
    Label idExist;
    @FXML
    Label adharExist;
    @FXML
    ScrollPane pane;
    @FXML
    ImageView showImage;
    @FXML
    RadioButton male;
    @FXML
    RadioButton female;
    @FXML
    RadioButton others;
    String gen;

    List<String> idList;

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void add() {
        try {
            boolean idAlreadyExist = false;
            boolean adharAlreadyExist = false;

            LocalDate d = join.getValue();

            for(String s : idList) {
                if((s.substring(0,s.indexOf('*'))).equals(id.getText())) {
                    idAlreadyExist = true;
                    break;
                }
            }

            for(String s : idList) {
                if((s.substring(s.indexOf('*')+1,s.length())).equals(adhaar.getText())) {
                    adharAlreadyExist = true;
                    break;
                }
            }

            gender();

            if((id.getText() == "") || (name.getText() == "") || (phone.getText() == "") || (email.getText() == "") ||
                    (adhaar.getText() == "") || (dep.getText() == "") || (state.getText() == "") || (city.getText() == "") ||
                    (join.getValue() == null) || (gen == null) || (showImage == null) || (file == null)) {
                pane.setVvalue(0);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty");
                alert.setContentText("Some Fields Are Empty");
                alert.showAndWait();

            } else {

                if (!idAlreadyExist && !adharAlreadyExist) {

                    String sqlInsert = "INSERT INTO employeerec (`Id`, `Name`, `Phone`, `Email`, `Aadhaar`, `Department`, `State`, `City`, `Joining`, `Photo`, `Gender`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                    ps = connection.prepareStatement(sqlInsert);

                    ps.setString(1, id.getText().trim());

                    ps.setString(2, name.getText());

                    ps.setString(3, phone.getText());

                    ps.setString(4, email.getText());

                    ps.setString(5, adhaar.getText().trim());

                    ps.setString(6, dep.getText());

                    ps.setString(7, state.getText());

                    ps.setString(8, city.getText());

                    ps.setString(9, (d.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).toString());

                    fis = new FileInputStream(file);
                    ps.setBinaryStream(10, (InputStream)fis, (int)file.length());

                    ps.setString(11, gen);

                    int executed = ps.executeUpdate();

                    if (executed > 0) {
                        idList.add(id.getText() +"*"+ adhaar.getText());
                        pane.setVvalue(0);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setContentText("Data Has Been Successfully Added To The DataBase");
                        alert.getButtonTypes().removeAll(ButtonType.OK, ButtonType.CANCEL);
                        alert.getButtonTypes().addAll(ButtonType.CLOSE);
                        alert.showAndWait();

                        flushCurrentTextFields();

                        idExist.setVisible(false);
                        adharExist.setVisible(false);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    if(idAlreadyExist && adharAlreadyExist) {
                        pane.setVvalue(0);
                        idExist.setVisible(true);
                        adharExist.setVisible(true);
                        System.out.println("I.D. and Aadhaar Number Already Exist");
                        alert.setTitle("Cannot Add Record");
                        alert.setContentText("Employee I.D. and Aadhaar Number Already Exist In The \nData Base");
                        alert.showAndWait();
                    }

                    else if(adharAlreadyExist) {
                        pane.setVvalue(0);
                        adharExist.setVisible(true);
                        idExist.setVisible(false);
                        System.out.println("Aadhaar Number Already Exist");
                        alert.setTitle("Cannot Add Record");
                        alert.setContentText("Aadhaar Number Already Exist In The Data Base");
                        alert.showAndWait();
                    }
                    else if(idAlreadyExist) {
                        pane.setVvalue(0);
                        idExist.setVisible(true);
                        adharExist.setVisible(false);
                        System.out.println("I.D. Already Exist");
                        alert.setTitle("Cannot Add Record");
                        alert.setContentText("Employee I.D. Already Exist In The Data Base");
                        alert.showAndWait();
                    }
                }
            }

        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Occcured code[404]");
            alert.setContentText(e.getMessage());
            System.out.println(e.getMessage());
            alert.showAndWait();
        }

    }

    public void unselectGender() {
        male.setSelected(false);
        female.setSelected(false);
        others.setSelected(false);
    }

    public void gender() {
        if(male.isSelected())
            gen = male.getText();
        else if(female.isSelected())
            gen = female.getText();
        else if(others.isSelected())
            gen = others.getText();
        else
            gen = null;
    }

    public void yourImage() {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        file = fileChooser.showOpenDialog(null);

        if(file != null) {
            Image img = new Image(file.toURI().toString(), 1500, 1500, true, true);
            showImage.setImage(img);
            showImage.setPreserveRatio(true);
        }
    }

    public void flushCurrentTextFields() {

        id.setText("");
        id.setPromptText("I.D. Number");

        name.setText("");
        name.setPromptText("Name");

        phone.setText("");
        phone.setPromptText("Phone Number");

        email.setText("");
        email.setPromptText("Email");

        adhaar.setText("");
        adhaar.setPromptText("Aadhaar Number");

        dep.setText("");
        dep.setPromptText("Department");

        state.setText("");
        state.setPromptText("State");

        city.setText("");
        city.setPromptText("City");

        join.setValue(null);
        join.setPromptText("Joining Date");

        file = null;
        fis = null;
        showImage.setImage(null);

        unselectGender();
        gen = null;

    }

    public void cancel(ActionEvent event) throws SQLException, IOException {
        connection.close();
        ps.close();
        idList.clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idList = new ArrayList<String>();
        idExist.setVisible(false);
        adharExist.setVisible(false);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Data Base Driver Loaded");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/officeemployees", "root", "");
            System.out.println("Connection is Established");

            String fetchSql = "SELECT Id,Aadhaar FROM employeerec";
            ps = connection.prepareStatement(fetchSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                idList.add(rs.getString("Id") + "*" + rs.getString("Aadhaar"));
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
