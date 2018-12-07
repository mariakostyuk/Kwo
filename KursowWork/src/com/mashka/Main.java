package com.mashka;

import com.mashka.conrollers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/sample.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();
            controller.setMainStage(primaryStage);
            primaryStage.setTitle("TaskManager");
            Scene scene = new Scene(root, 1300, 900);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e){
             e.printStackTrace();
         }
    }

        public static void main(String[] args) {
            launch(args);
        }

}

