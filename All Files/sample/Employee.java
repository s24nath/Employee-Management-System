package sample;

import javafx.beans.property.SimpleStringProperty;

public class Employee implements Comparable<Employee> {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty dep;
    private SimpleStringProperty phone;
    private SimpleStringProperty dateOfJoining;

    public Employee(String id, String name, String dep, String phone, String dateOfJoining) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.dep = new SimpleStringProperty(dep);
        this.phone = new SimpleStringProperty(phone);
        this.dateOfJoining = new SimpleStringProperty(dateOfJoining);
    }

    // I.D.
    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    // Name
    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    // Department
    public SimpleStringProperty depProperty() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep.set(dep);
    }

    // Phone
    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    // Date Of joining
    public SimpleStringProperty dateOfJoiningProperty() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining.set(dateOfJoining);
    }

    @Override
    public int compareTo(Employee o) {
        return name.get().compareTo(o.name.get());
    }
}
