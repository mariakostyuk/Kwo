package com.mashka.conrollers;

import com.mashka.conrollers.utils.Returnable;
import com.mashka.models.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EnterController implements Returnable {

    public static Task task;

    @FXML
    private javafx.scene.control.Button OkButton;
    @FXML
    private TextField FirstText;
    @FXML
    private TextField SecondText;

    Alert alertI = new Alert(Alert.AlertType.INFORMATION);
    Alert alertW = new Alert(Alert.AlertType.WARNING);

    /**
     * Активна кнопка OK
     * Подтверждение добавления новой задачи
     */
    @FXML
    private void OkButtonOnAction() {
        String textComplexity = FirstText.getText();
        String textTitle = SecondText.getText();
        if (verifyTask(textComplexity)){
            alertI.setTitle("INFORMATION");
            alertI.setHeaderText(null);
            alertI.setContentText("Task accepted");
            alertI.showAndWait();
            task = verifyTask(textTitle, textComplexity);
        }else{
            alertW.setTitle("WARNING");
            alertW.setHeaderText(null);
            alertW.setContentText("Incorrect data entered");
            alertW.showAndWait();
        }

      //  task = verifyTask(textTitle, textComplexity);
        Stage stage = (Stage) OkButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Проверка ввода новой задачи
     * @param comlexity
     * @return
     */
    private Boolean verifyTask(String comlexity){
        int intik;
        try{
            intik = Integer.parseInt(comlexity);
            if (intik <= 0){
                return false;
            }
            return true;
        }
        catch (RuntimeException ex){
            return false;
        }
    }

    /**
     * Проверка ввода новой задачи
     * @param title
     * @param comlexity
     * @return
     */
    private Task verifyTask(String title, String comlexity)
    {
        int val = -1;
        if (title.isEmpty())
            throw new RuntimeException("Error: empty task");
        try {
            val = Integer.parseInt(comlexity);
            if (val <= 0)
                throw new RuntimeException("Error: illegal complexity");
        }catch (RuntimeException ex) {
            throw ex;
        }
        return new Task(val, title);
    }

    public Task getReturn() {
        String textComplexity = FirstText.getText();
        String textTitle = SecondText.getText();
        task = verifyTask(textTitle, textComplexity);
        Stage stage = (Stage) OkButton.getScene().getWindow();
        stage.close();
        return task;
    }

}
