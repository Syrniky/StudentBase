package sample;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Label l2 = new Label();
    public static FlowPane root;
    @Override
    public void start(Stage primaryStage) throws InterruptedException, IOException{
        tableview.actors();
        tableview.tabl();

        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);
        root = new FlowPane(Orientation.VERTICAL, 10, 10, tableview.table,tableview.l1,tableview.butt,tableview.delete,
                tableview.refresh,tableview.search,tableview.info);
        Scene scene = new Scene(root, 1050, 550);
        primaryStage.setScene(scene);
        root.getChildren().addAll(l2);
        primaryStage.setTitle("База даних студентів");
        primaryStage.show();
    }

    public static void update() {
        tableview.people.removeAll(tableview.st);
        tableview.people.addAll(tableview.st);
    }

    public static void main(String[] args) {
        launch(args);
    }

}