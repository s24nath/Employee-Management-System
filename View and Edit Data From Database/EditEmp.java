package sample;

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
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Formatter;

public class EditEmp implements Initializable {

    FileChooser fileChooser = new FileChooser();
    File file;
    FileInputStream fis;

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String oldId;
    String oldName;
    String oldPhone;
    String oldEmail;
    String oldDep;
    String oldState;
    String oldCity;
    String oldJoin;
    String oldGen;
    @FXML
    Image oldImg;
    boolean newImgSet = false;

    @FXML
    Label id;
    @FXML
    TextField name;
    @FXML
    TextField phone;
    @FXML
    TextField email;
    @FXML
    TextField adhar;
    @FXML
    TextField dep;
    @FXML
    TextField state;
    @FXML
    TextField city;
    @FXML
    TextField join;
    @FXML
    ImageView showImage;
    @FXML
    RadioButton male;
    @FXML
    RadioButton female;
    @FXML
    RadioButton others;
    @FXML
    ScrollPane pane;
    String gen;
    String curradhar;
    @FXML
    Image image;
    ArrayList<String> adharList;

    @FXML
    public void update(ActionEvent event) throws SQLException, IOException {
        boolean adharAlreadyExist = false;

        for(String s : adharList) {
            if(s.equals(adhar.getText())) {
                if(!s.equals(curradhar))
                    adharAlreadyExist = true;
            }
        }

        setGender();
        if((name.getText() == "") || (phone.getText() == "") || (email.getText() == "") ||
                (adhar.getText() == "") || (dep.getText() == "") || (state.getText() == "") || (city.getText() == "") ||
                (join.getText() == "") || (gen == null) || (showImage == null)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty");
            alert.setContentText("Some Fields Are Empty");
            alert.showAndWait();

        }
        else {

            if(!adharAlreadyExist) {

                if(newImgSet) {

                    String update = "UPDATE employeerec SET Name = ?, Phone = ?, Email = ?, Aadhaar = ?, Department = ?, " +
                            "State = ?, City = ?, Joining = ?, Gender = ?, Photo = ? Where Id = ?" ;

                    ps = connection.prepareStatement(update);

                    ps.setString(1,name.getText());
                    ps.setString(2,phone.getText());
                    ps.setString(3,email.getText());
                    ps.setString(4,adhar.getText().trim());
                    ps.setString(5,dep.getText());
                    ps.setString(6,state.getText());
                    ps.setString(7,city.getText());
                    ps.setString(8, join.getText());
                    ps.setString(9,gen);

                    fis = new FileInputStream(file);
                    ps.setBinaryStream(10, (InputStream)fis, (int)file.length());

                    ps.setString(11,id.getText());

                } else {

                    String update = "UPDATE employeerec SET Name = ?, Phone = ?, Email = ?, Aadhaar = ?, Department = ?, " +
                            "State = ?, City = ?, Joining = ?, Gender = ? Where Id = ?" ;

                    ps = connection.prepareStatement(update);

                    ps.setString(1,name.getText());
                    ps.setString(2,phone.getText());
                    ps.setString(3,email.getText());
                    ps.setString(4,adhar.getText().trim());
                    ps.setString(5,dep.getText());
                    ps.setString(6,state.getText());
                    ps.setString(7,city.getText());
                    ps.setString(8, join.getText());
                    ps.setString(9,gen);
                    ps.setString(10,id.getText());

                }

/*                if(EmpTable.selectedEmpRow != null) {
                    EmpTable.selectedEmpRow.setName(name.getText());
                    EmpTable.selectedEmpRow.setPhone(phone.getText());
                    EmpTable.selectedEmpRow.setDep(dep.getText());
                    EmpTable.selectedEmpRow.setDateOfJoining(join.getText());
                }*/

                int executed = ps.executeUpdate();

                if(executed > 0) {

                    connection.close();
                    ps.close();
                    rs.close();
                    adharList.clear();
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("viewemployee.fxml"));
                    Parent root = (Parent) loader.load();
                    ViewEmp viewEmp = loader.getController();
                    viewEmp.setLabels(id.getText(),name.getText(),phone.getText(),email.getText(),adhar.getText(),dep.getText(),
                            state.getText(),city.getText(),join.getText(),showImage.getImage(),gen);

                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setContentText("Data Has Been Successfully Updated To The DataBase");
                    alert.getButtonTypes().removeAll(ButtonType.OK, ButtonType.CANCEL);
                    alert.getButtonTypes().addAll(ButtonType.CLOSE);
                    alert.showAndWait();

                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("There Is A Problem In Updating");
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cannot Update Record");
                alert.setContentText("Aadhaar Number Already Exist In The Data Base");
                alert.showAndWait();
            }
        }
    }

    public void setText(String l1, String l2, String l3, String l4, String l5, String l6, String l7, String l8, String l9, Image img, String l11) {
        id.setText(l1);
        oldId = l1;

        name.setText(l2);
        oldName = l2;

        phone.setText(l3);
        oldPhone = l3;

        email.setText(l4);
        oldEmail = l4;

        adhar.setText(l5);
        curradhar = l5;

        dep.setText(l6);
        oldDep = l6;

        state.setText(l7);
        oldState = l7;

        city.setText(l8);
        oldCity = l8;

        join.setText(l9);
        oldJoin = l9;

        oldImg = img;
        showImage.setImage(img);

        getGender(l11);
        oldGen = l11;
    }

    public void getGender(String s) {
        if(s.equals("Male")) {
            male.setSelected(true);
        }
        else if(s.equals("Female")) {
            female.setSelected(true);
        }
        else {
            others.setSelected(true);
        }
    }

 public void setGender() {
        if(male.isSelected())
            gen = male.getText();
        else if(female.isSelected())
            gen = female.getText();
        else if(others.isSelected())
            gen = others.getText();
    }


    public void setImage() {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        file = fileChooser.showOpenDialog(null);

        if(file != null) {
            Image img = new Image(file.toURI().toString(), 1500, 1500, true, true);
            showImage.setImage(img);
            newImgSet = true;
            showImage.setPreserveRatio(true);
        }
    }

    public void cancel(ActionEvent event) throws IOException, SQLException {

        adharList.clear();
        connection.close();
        ps.close();
        setGender();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewemployee.fxml"));
        Parent root = (Parent) loader.load();
        ViewEmp viewEmp = loader.getController();
        viewEmp.setLabels(oldId, oldName , oldPhone, oldEmail, curradhar, oldDep,
                oldState, oldCity, oldJoin, oldImg, oldGen);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adharList = new ArrayList<String>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Data Base Driver Loaded");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/officeemployees", "root", "");
            System.out.println("Connection is Established");

            String fetchSql = "SELECT Aadhaar FROM employeerec";
            ps = connection.prepareStatement(fetchSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                adharList.add(rs.getString("Aadhaar"));
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
