import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SearchForDelete implements Initializable {

    @FXML
    TextField id;

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    public void search(ActionEvent event) {

        try {
            String fetchSql = "SELECT * FROM employeerec WHERE Id=?";
            ps = connection.prepareStatement(fetchSql);
            ps.setString(1,id.getText());
            rs = ps.executeQuery();

            if(rs.next()) {
                try {

                    InputStream is = rs.getBinaryStream(10);
                    OutputStream os = new FileOutputStream(new File("photo.jpg"));
                    byte[] content = new byte[1024];
                    int size = 0;
                    while((size = is.read(content)) != -1) {
                        os.write(content, 0, size);
                    }
                    os.close();
                    is.close();
                    Image img = new Image("file:photo.jpg",1000,1500,true,true);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteView.fxml"));
                    Parent root = (Parent) loader.load();
                    DeleteView deleteView = loader.getController();
                    deleteView.setLabels(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                            rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                            rs.getString(9), img, rs.getString(11));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch(IOException exception) {
                    System.out.println(exception.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Employee Not Found");
                System.out.println("Employee Not Found");
                alert.showAndWait();
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void back(ActionEvent event) throws IOException, SQLException {

        connection.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
