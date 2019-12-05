package sample;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SearchResults {

    public static void show(ArrayList<Students> students) {
        Stage resultWindow = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);

        TableView<Students> studentTable = studentTable();
        Button showButton = new Button("Вивести інформацію");
        showButton.relocate(10, 430);
        showButton.setOnAction(event -> control.inform(studentTable.getSelectionModel().getSelectedItem()));

        Students.sort(students);
        studentTable.setItems(FXCollections.observableArrayList(students));
        root.getChildren().addAll(studentTable, showButton);
        resultWindow.setScene(scene);
        resultWindow.show();
    }

    private static javafx.scene.control.TableView<Students> studentTable() {
        TableView<Students> table = new javafx.scene.control.TableView<>();
        table.setEditable(true);
        table.relocate(10, 10);
        TableColumn<Students,String> predmet = new TableColumn<Students, String>("Ім'я");
        predmet.setCellValueFactory(
                new PropertyValueFactory<Students, String>("name")
        );

        predmet.setMinWidth(140);
        table.getColumns().addAll(predmet);
        return table;
    }
}
