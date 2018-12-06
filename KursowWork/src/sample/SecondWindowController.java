package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondWindowController {
    private ObservableList<Promotion> promotionList = FXCollections.observableArrayList();
    private Controller controller;
    private Integer tempScore;
    @FXML
    private Label scoreTextLabelTwo = new Label();
    @FXML
    private TableView<Promotion> promotionTable;
    @FXML
    private TableColumn<Promotion, Integer> costColumn;
    @FXML
    private TableColumn<Promotion, String> titleColumn;
    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private void GoToHome() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Чтение списка поощрений из файла
     * @param fileName
     * @param taskData
     */
    private void readFromFile(String fileName, ObservableList<Promotion> taskData){
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine = new String();
            String temp = new String();
            String name = new String();
            int number = 0;
            boolean f = true;
            while ((strLine = br.readLine()) != null) {
                if ((strLine.equals(""))) {
                } else {
                    if (f) {
                        number = Integer.parseInt(strLine);
                        f = false;
                    } else {
                        name = strLine;
                        taskData.add(new Promotion(number, name));
                        f = true;
                    }
                }
            }
            fstream.close();
        } catch (IOException e) {
            System.out.println("Error: read error");
        }
    }

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public SecondWindowController() {
        readFromFile("Promotion.txt", promotionList );
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        scoreTextLabelTwo.setText("Score");
        //Инициализация таблицы
        costColumn.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        setPromotionList();
        promotionTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> ChoosePromotion(newValue));
    }

    private void setPromotionList() {
        promotionTable.setItems(getPromotionList());
    }

    /**
     * Возвращает данные в виде наблюдаемого списка поощрений.
     * @return
     */
    private ObservableList<Promotion> getPromotionList() {
        return promotionList ;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Активация кнопки
     */
    public void ShopAddButtonOnAction(){
        scoreTextLabelTwo.setText(String.valueOf(controller.getScore() - tempScore));
        controller.setScore(controller.getScore() - tempScore);
    }

    /**
     * Определение активной строки
     * @param promotion
     */
    private void ChoosePromotion(Promotion promotion) {
        if (promotion != null) {
            tempScore = promotion.getCost();
            scoreTextLabelTwo.setText(String.valueOf(controller.getScore()));
        }
    }
}
