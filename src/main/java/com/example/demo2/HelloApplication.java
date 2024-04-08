package com.example.demo2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {

    // Field for ResourceBundle
    private ResourceBundle bundle;
    private String lang;


    @Override
    public void start(Stage primaryStage) throws SQLException {
        ComboBox<String> languageComboBox = new ComboBox<>(FXCollections.observableArrayList("English", "فارسی", "日本語"));
        languageComboBox.getSelectionModel().selectFirst(); // Default to English
        lang = "en";
        // At application start
        Locale defaultLocale = Locale.ENGLISH; // Fallback to English if Finnish is the system default
        bundle = ResourceBundle.getBundle("Bundle", defaultLocale);

        // UI Components
        Label firstNameLabel = new Label(bundle.getString("firstName"));
        TextField firstNameTextField = new TextField();
        Label lastNameLabel = new Label(bundle.getString("lastName"));
        TextField lastNameTextField = new TextField();
        Label emailLabel = new Label(bundle.getString("email"));
        TextField emailTextField = new TextField();


        // Buttons
        Button saveButton = new Button(bundle.getString("save"));


        // Language Selection Handling
        languageComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            Platform.runLater(() -> {
                Locale locale;
                switch (newVal) {
                    case "فارسی":
                        locale = new Locale("fa");
                        lang = "fa";
                        break;
                    case "日本語":
                        locale = new Locale("ja");
                        lang = "ja";
                        break;
                    case "English":
                        locale = new Locale("en");
                        lang = "en";
                        break;
                    default:
                        locale = defaultLocale;
                        break;
                };
                // Update the field instead of a local variable
                System.out.println("Locale changed to: " + locale);
                System.out.println("First Name Label Text: " + bundle.getString("firstName"));

                bundle = ResourceBundle.getBundle("Bundle", locale);
                updateLabels(firstNameLabel, lastNameLabel, emailLabel, saveButton);
            });
        });
        saveButton.setOnAction(e -> {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String email = emailTextField.getText();
            String languageCode = lang;
            try {
                DatabaseHelper.addEmployee(firstName, lastName, email,languageCode);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });


        VBox layout = new VBox(10); // 10px spacing
        layout.getChildren().addAll(languageComboBox, firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField, emailLabel, emailTextField, saveButton);

        Scene scene = new Scene(layout, 400, 500); // Adjust size as needed
        primaryStage.setScene(scene);
        primaryStage.setTitle("Localized Employee App");
        primaryStage.show();
    }

    private void updateLabels(Label firstNameLabel, Label lastNameLabel, Label emailLabel, Button saveButton) {

        firstNameLabel.setText(bundle.getString("firstName"));
        lastNameLabel.setText(bundle.getString("lastName"));
        emailLabel.setText(bundle.getString("email"));
        saveButton.setText(bundle.getString("save"));

    }

    public static void main(String[] args) {
        launch(args);
    }
}