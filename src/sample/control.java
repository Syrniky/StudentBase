package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class control {
    public static ObservableList<String> teachers = FXCollections.observableArrayList(
            "Денисюк Алла Василівна",
            "Войтко Вікторія Володимирівна",
            "Коваленко Олена Олексіївна",
            "Гаврилюк Олена Віталіївна",
            "Майданюк Володимир Павлович",
            "Ракитянська Ганна Борисівна"
    );

    public static ObservableList<String> groups = FXCollections.observableArrayList(
            "1ПІ-18б",
            "2ПІ-18б",
            "3ПІ-18б"
    );

    public static void addstudents() {
        Stage createWindow = new Stage();
        GridPane root = new GridPane();
        Scene scene = new Scene(root);

        root.setPadding(new Insets(25));
        root.setVgap(10);
        root.setHgap(10);


        Text title = new Text("Додати студента");
        title.setFont(Font.font(20));
        HBox titleContainer = new HBox(title);
        titleContainer.setAlignment(Pos.TOP_CENTER);
        root.add(titleContainer, 0, 0, 2, 1);

        Label nameLabel = new Label("Ім'я:");
        root.add(nameLabel, 0, 1);
        TextField nameField = new TextField();
        root.add(nameField, 1, 1);

        Label ageLabel = new Label("Вік:");
        root.add(ageLabel, 0, 2);
        Spinner<Integer> ageSpinner = new Spinner<>(16, 25, 0);
        root.add(ageSpinner, 1, 2);

        Label kursLabel = new Label("Курс:");
        root.add(kursLabel, 0, 3);
        Spinner<Integer> kursSpinner = new Spinner<>(1, 5, 0);
        root.add(kursSpinner, 1, 3);

        Label iqLabel = new Label("Група:");
        root.add(iqLabel, 2, 3);
        ChoiceBox<String> choicegroups = new ChoiceBox<String>(groups);
        root.add(choicegroups, 3, 3);

        Label label1=new Label("Середній бал:");
        root.add(label1, 0, 4);
        TextField textField1 = new TextField();
        root.add(textField1, 1, 4);

        Label label2=new Label("Куратор:");
        root.add(label2, 0, 5);
        ChoiceBox<String> langsChoiceBox = new ChoiceBox<String>(teachers);
        root.add(langsChoiceBox, 1, 5);


        Label typeLabel = new Label("Форма навчання:");
        root.add(typeLabel, 0, 6);

        ToggleGroup switchGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton("Контрактна");
        radioButton1.setToggleGroup(switchGroup);
        radioButton1.setSelected(true);
        root.add(radioButton1, 1, 6);

        RadioButton radioButton2 = new RadioButton("Бюджетна");
        radioButton2.setToggleGroup(switchGroup);
        root.add(radioButton2, 1, 7);

        //CheckBox selectChoice = new CheckBox("Взять под контроль");
        Button confirmButton = new Button("Додати до бази даних");
        confirmButton.getStyleClass().add("success");
        VBox bottomContainer = new VBox(10, confirmButton);
        bottomContainer.setAlignment(Pos.BOTTOM_CENTER);
        root.add(bottomContainer, 0, 10, 2, 1);

        confirmButton.setOnAction(event -> {
            String name = nameField.getText();
            int age = ageSpinner.getValue();
            int kurs = kursSpinner.getValue();
            String gruppa = choicegroups.getValue();
            String sredniyball = textField1.getText();
            String kurator = langsChoiceBox.getValue();


            if(age==0 || kurs==0 || name==null || gruppa==null || sredniyball==null|| kurator==null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка");
                alert.setHeaderText("Помилка при додаванні студента до бази даних");
                alert.setContentText("Потрібно заповнити всі поля");
                alert.showAndWait();
            } else {
                Students newstudents = new Students(name, age, gruppa, kurs, kurator, Double.parseDouble(sredniyball));
                tableview.st.add(newstudents);
                tableview.table.refresh();
                Main.update();
                createWindow.close();


            }
        });
        createWindow.setTitle("Додати студента");
        createWindow.setResizable(false);
        createWindow.setScene(scene);
        createWindow.show();
    }

    public static void search() {
        Stage createWindow = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root, 300, 300);

        Text title = new Text("Введіть ім'я");
        title.setFont(Font.font(20));
        HBox titleContainer = new HBox(title);
        titleContainer.setAlignment(Pos.TOP_CENTER);
        title.relocate(20,10);

        TextField searchField = new TextField();
        searchField.relocate(10,60);

        Button searchButton = new Button("Знайти");
        searchButton.relocate(10, 120);
        searchButton.setOnAction(event -> {
            String toSearch = searchField.getText();
            ArrayList<Students> studentsArrayList = new ArrayList<>();
            for (Students st: tableview.table.getItems()) {
                if (st.getName().contains(toSearch))
                    studentsArrayList.add(st);
            }
            SearchResults.show(studentsArrayList);
        });

        root.getChildren().addAll(title, searchField, searchButton);
        createWindow.setTitle("Шукати студента");
        createWindow.setResizable(false);
        createWindow.setScene(scene);
        createWindow.show();
    }

    public static void deletestudents(Students t1) {
        Stage createWindow = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);

        root.setPadding(new Insets(25));

        Text title = new Text("Видалити студента");
        title.setFont(Font.font(20));
        HBox titleContainer = new HBox(title);
        titleContainer.setAlignment(Pos.TOP_CENTER);
        title.relocate(65,10);

        Text title1 = new Text("Ви впевненні?");
        title1.setFont(Font.font(16));
        HBox titleContainer1 = new HBox(title1);
        titleContainer1.setAlignment(Pos.TOP_CENTER);
        title1.relocate(95,70);

        Button b1 = new Button("Так");
        b1.setFont(Font.font(14));
        b1.setOnAction(event -> {
            tableview.people.removeAll(tableview.st);
            tableview.st.remove(t1);
            tableview.people.addAll(tableview.st);
            createWindow.close();
            Main.update();
        });
        b1.relocate(40,120);

        Button b2 = new Button("Hi");
        b2.setFont(Font.font(14));
        b2.setOnAction(e -> createWindow.close());
        b2.relocate(220,120);

        root.getChildren().addAll(title, title1, b2, b1);
        createWindow.setTitle("Видалити студента");
        createWindow.setResizable(false);
        createWindow.setScene(scene);
        createWindow.show();
    }

    public static void inform(Students t1){
        Stage createWindow = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);

        Text title = new Text("Інформація про студента на ім'я "+t1.getName());
        title.setFont(Font.font(16));
        HBox titleContainer = new HBox(title);
        titleContainer.setAlignment(Pos.TOP_CENTER);
        title.setLayoutY(30);

        Label name = new Label(t1.toString());
        name.setFont(Font.font(14));
        name.relocate(5,50);

        TableView<Ozinka> zachotka = zachotka();
        zachotka.setItems(FXCollections.observableArrayList(t1.getZachotka().getOzinky()));

        Button addOzinka = new Button("Додати оцінку");
        addOzinka.setLayoutY(600);
        Button shell = new Button("Відсортувати");
        shell.setLayoutY(650);
        Button changeOzinka = new Button("Змінити оцінку");
        changeOzinka.setLayoutY(700);

        addOzinka.setOnAction(event -> {
            newOzinka(t1);
            zachotka.setItems(FXCollections.observableArrayList(t1.getZachotka().getOzinky()));
            name.setText(t1.toString());
        });
        shell.setOnAction( event -> {
            t1.getZachotka().sort();
            zachotka.setItems(FXCollections.observableArrayList(t1.getZachotka().getOzinky()));
        });
        changeOzinka.setOnAction(event -> {
            Ozinka toChange = zachotka.getSelectionModel().getSelectedItem();
            if (toChange == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка");
                alert.setHeaderText("Помилка при зміні оцінки");
                alert.setContentText("Нічого не вибрано");
                alert.showAndWait();
                return;
            }
            t1.getZachotka().getOzinky().set(t1.getZachotka().getOzinky().indexOf(toChange), newOzinka());
            zachotka.setItems(FXCollections.observableArrayList(t1.getZachotka().getOzinky()));
        });

        root.setMinWidth(400);
        root.setMinHeight(300);


        root.getChildren().addAll(title, name, zachotka, addOzinka, shell, changeOzinka);
        createWindow.setTitle("Інформація про студента");
        createWindow.setResizable(false);
        createWindow.setScene(scene);
        createWindow.show();
    }

    private static void newOzinka(Students student) {
        student.getZachotka().addOzinka(newOzinka());
    }

    private static Ozinka newOzinka() {
        Stage ozinkaWindow = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);
        Label predmetLabel = new Label("Введіть предмет");
        TextField predmet = new TextField();
        Label ozinkaLabel = new Label("Введіть оцінку");
        TextField ozinka = new TextField();

        Button confirm = new Button("Підтвердити");

        predmetLabel.setLayoutY(10);
        predmet.setLayoutY(50);
        ozinkaLabel.setLayoutY(100);
        ozinka.setLayoutY(150);// TODO пограєшся щоб KpacuBo було
        confirm.setLayoutY(200);

        final Ozinka[] returnValue = new Ozinka[1];
        confirm.setOnAction(event -> {
            if(predmet.getText() == null || ozinka.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка");
                alert.setHeaderText("Помилка при додаванні оцінки");
                alert.setContentText("Потрібно заповнити всі поля");
                alert.showAndWait();
            }
            else{
                returnValue[0] = new Ozinka(predmet.getText(), Double.parseDouble(ozinka.getText()));
//                student.getZachotka().addOzinka(predmet.getText(), Double.parseDouble(ozinka.getText()));
                ozinkaWindow.close();
            }
        });
        ozinkaWindow.setScene(scene);
        root.getChildren().addAll(predmet, ozinka, confirm, predmetLabel, ozinkaLabel);
        ozinkaWindow.showAndWait();
        return returnValue[0];
    }

    private static TableView<Ozinka> zachotka() {
        TableView<Ozinka> table = new TableView<>();
        table.setEditable(true);
        table.relocate(0, 180);
        TableColumn<Ozinka,String> predmet = new TableColumn<Ozinka, String>("Предмет");
        TableColumn<Ozinka,Double> ozinka = new TableColumn<Ozinka, Double>(" Оцінка");
        predmet.setCellValueFactory(
                new PropertyValueFactory<Ozinka, String>("predmet")
        );
        ozinka.setCellValueFactory(
                new PropertyValueFactory<Ozinka,Double>("ozinka")
        );
        predmet.setMinWidth(140);
        ozinka.setMinWidth(140);
        table.getColumns().addAll(predmet,ozinka);
        return table;
    }
}
