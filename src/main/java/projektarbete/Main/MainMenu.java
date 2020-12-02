package projektarbete.Main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import projektarbete.Controller.ContactBook;

import java.util.ArrayList;

import projektarbete.Controller.ContactBook;
import projektarbete.Model.WorkContact;
import projektarbete.data.ioHandling;

import java.util.HashMap;

public class MainMenu extends Application {

    public static HashMap<String, WorkContact> workContactHashMap = ioHandling.loadHashMapFromJson();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        ContactBook.printContactBook();


        BorderPane mainBorderPane = new BorderPane();
        mainBorderPane.setMinSize(600, 400);

        ArrayList<String> firstNamesArrayList = new ArrayList<String>(workContactHashMap.keySet());

        ListView<String> listView = new ListView();
        ObservableList<String> contactsFromHashMap =
                FXCollections.observableArrayList(
                        firstNamesArrayList
                );
        listView.setItems(contactsFromHashMap);

        mainBorderPane.setRight(listView);
        MenuBar menuBar = new MenuBar();
        mainBorderPane.setTop(menuBar);

        Menu fileMenu = new Menu("File");

        MenuItem loadMenu = new MenuItem("Load");
        MenuItem saveMenu = new MenuItem("Save");
        MenuItem exitMenu = new MenuItem("Exit");

        fileMenu.getItems().add(loadMenu);
        fileMenu.getItems().add(saveMenu);
        fileMenu.getItems().add(exitMenu);

        loadMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        saveMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        exitMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ioHandling.saveHashMapToJson(workContactHashMap);
                stage.close();
            }
        });


        menuBar.getMenus().addAll(fileMenu);
        menuBar.setMaxWidth(600);

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(250, 270);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(20);
        gridPane.setHgap(2);
        gridPane.setGridLinesVisible(false);

        mainBorderPane.setLeft(gridPane);

        Button addButton = new Button("Add contact");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreateNewContact createNewContact = new CreateNewContact();
                createNewContact.start(stage);
            }
        });

        Button searchButton = new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SearchContact searchContact = new SearchContact();
                searchContact.start(stage);
            }
        });

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RemoveContact removeContact = new RemoveContact();
                removeContact.start(stage);
            }
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UpdateContact updateContact = new UpdateContact();
                updateContact.start(stage);
            }
        });


        gridPane.add(addButton, 0,0);
        gridPane.add(searchButton,0,1);
        gridPane.add(removeButton,0,2);
        gridPane.add(updateButton,0,3);

        Scene scene = new Scene(mainBorderPane);
        stage.setScene(scene);
        stage.show();

    }
}
