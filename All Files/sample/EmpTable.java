package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmpTable implements Initializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @FXML
    TableView<Employee> tableView;
    @FXML
    TableColumn<Employee, String> idCol;
    @FXML
    TableColumn<Employee, String> nameCol;
    @FXML
    TableColumn<Employee, String> depCol;
    @FXML
    TableColumn<Employee, String> phoneCol;
    @FXML
    TableColumn<Employee, String> joiningCol;

    @FXML
    TextField search;

    ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    static Employee selectedEmpRow;

    public void view() {

        selectedEmpRow = tableView.getSelectionModel().getSelectedItem();

        if(selectedEmpRow != null) {
            try {

                String fetchSql = "SELECT * FROM employeerec WHERE Id=?";
                ps = connection.prepareStatement(fetchSql);

                ps.setString(1,selectedEmpRow.idProperty().get());

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

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewemployee.fxml"));
                        Parent root = (Parent) loader.load();
                        ViewEmp viewEmp = loader.getController();
                        viewEmp.setLabels(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                                rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                                rs.getString(9), img, rs.getString(11));
                        viewEmp.scrollUp();
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

        } else return;

    }

    public void backToMenu(ActionEvent event) throws IOException, SQLException {

        selectedEmpRow = null;
        employeeList.clear();
        connection.close();
        ps.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void searchFilter() {

        FilteredList<Employee> filteredList = new FilteredList<Employee>(employeeList);

        search.textProperty().addListener(observable -> {
            filteredList.setPredicate(employee -> {

                if(search.getText() == null || search.getText().isEmpty()) {
                    return true;
                }

                String lowerCasing = search.getText().toLowerCase().trim();

                if(employee.idProperty().get().toLowerCase().contains(lowerCasing) == true) {
                    return true;
                }

                if(employee.nameProperty().get().toLowerCase().contains(lowerCasing) == true) {
                    return true;
                }

                if(employee.depProperty().get().toLowerCase().contains(lowerCasing) == true) {
                    return true;
                }

                if(employee.dateOfJoiningProperty().get().toLowerCase().contains(lowerCasing) == true) {
                    return true;
                }

                return false;
            });
        });

        SortedList<Employee> sortedList = new SortedList<Employee>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Data Base Driver Loaded");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/officeemployees", "root", "");
            System.out.println("Connection is Established");

            String fetchSql = "SELECT * FROM employeerec";
            ps = connection.prepareStatement(fetchSql);
            rs = ps.executeQuery();

            while(rs.next()) {
                employeeList.add(new Employee(rs.getString("Id"), rs.getString("Name"),
                        rs.getString("Department"),rs.getString("Phone"),rs.getString("Joining")));
            }

            idCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
            depCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("dep"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("phone"));
            joiningCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("dateOfJoining"));

            employeeList.sort(Employee::compareTo);
            tableView.setItems(employeeList);

            searchFilter();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
