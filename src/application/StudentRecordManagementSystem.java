package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentRecordManagementSystem extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("StudentRecords Record Management System");

        // Menu options
        Button addStudentButton = new Button("Add New StudentRecords");
        Button updateStudentButton = new Button("Update StudentRecords Information");
        Button viewStudentsButton = new Button("View StudentRecords Details");

        // Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(addStudentButton, updateStudentButton, viewStudentsButton);

        // Scene
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event handlers
        addStudentButton.setOnAction(e -> showAddStudentDialog());
        updateStudentButton.setOnAction(e -> showUpdateStudentDialog());
        viewStudentsButton.setOnAction(e -> showViewStudentsDialog());
    }

    private void showAddStudentDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Add New StudentRecords");

        VBox dialogVbox = new VBox(10);
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        TextField gradeField = new TextField();
        gradeField.setPromptText("Grade");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            int age = Integer.parseInt(ageField.getText());
            String grade = gradeField.getText();
            StudentManagement.addStudent(new StudentRecords(name, id, age, grade));
            dialog.close();
        });

        dialogVbox.getChildren().addAll(nameField, idField, ageField, gradeField, addButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }


    private void showUpdateStudentDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Update StudentRecords Information");

        VBox dialogVbox = new VBox(10);
        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        TextField gradeField = new TextField();
        gradeField.setPromptText("Grade");

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            String id = idField.getText();
            StudentRecords studentRecords = StudentManagement.findStudentById(id);
            if (studentRecords != null) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String grade = gradeField.getText();
                StudentManagement.updateStudent(id, name, age, grade);
                dialog.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("StudentRecords ID not found!");
                alert.show();
            }
        });

        dialogVbox.getChildren().addAll(idField, nameField, ageField, gradeField, updateButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }


    private void showViewStudentsDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("View StudentRecords Details");

        // TableView to display student details
        TableView<StudentRecords> tableView = new TableView<>();

        // Define columns
        TableColumn<StudentRecords, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<StudentRecords, String>("name"));

        TableColumn<StudentRecords, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<StudentRecords, String>("id"));

        TableColumn<StudentRecords, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<StudentRecords, Integer>("age"));

        TableColumn<StudentRecords, String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<StudentRecords, String>("grade"));

        // Add columns to the table
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(ageColumn);
        tableView.getColumns().add(gradeColumn);
       
        // Add student data to the table
        tableView.getItems().addAll(StudentManagement.getAllStudents());

        // Layout
        VBox dialogVbox = new VBox(10);
        dialogVbox.getChildren().add(tableView);

        // Scene
        Scene dialogScene = new Scene(dialogVbox, 400, 300);
        dialog.setScene(dialogScene);
        dialog.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
