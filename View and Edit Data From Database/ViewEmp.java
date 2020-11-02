
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class ViewEmp implements Initializable {

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
    @FXML
    Image image;

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

        image = img;
        photo.setImage(img);

        gender.setText(l11);

    }

    public void scrollUp() {

        pane.setVvalue(0);

    }

    public void edit(ActionEvent event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("editemp.fxml"));
        Parent root = (Parent) loader.load();
        EditEmp editEmp = loader.getController();

        editEmp.setText(id.getText(),name.getText(),phone.getText(),email.getText(),adhar.getText(),dep.getText(),
                state.getText(),city.getText(),join.getText(),image,gender.getText());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void cancel(ActionEvent event) throws SQLException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //EmpTable.selectedEmpRow = null;
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
