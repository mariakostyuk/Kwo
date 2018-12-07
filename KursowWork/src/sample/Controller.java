package sample;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;



@SuppressWarnings("ALL")
public class Controller {
    private int flag;
    private boolean flagScopOut;
    private int tablePosition;
    private Integer score = 100;
    private ObservableList<Task> taskDataDay = FXCollections.observableArrayList();
    private ObservableList<Task> taskDataWeek = FXCollections.observableArrayList();
    private ObservableList<Task> taskDataMonth = FXCollections.observableArrayList();
    private ObservableList<Task> taskDataYear = FXCollections.observableArrayList();
    @FXML
    private Button removeSelectionButton;
    @FXML
    private Button deleteButton;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label scoreTextLebel = new Label();
    @FXML
    private Button upgreateButton;
    @FXML
    private TableView<Task> taskTableDay;
    @FXML
    private TableView<Task> taskTableWeek;
    @FXML
    private TableView<Task> taskTableMonth;
    @FXML
    private TableView<Task> taskTableYear;
    @FXML
    private TableColumn<Task, Integer> comlexityColumnDay;
    @FXML
    private TableColumn<Task, String> titleColumnDay;
    @FXML
    private TableColumn<Task, Integer> comlexityColumnWeek;
    @FXML
    private TableColumn<Task, String> titleColumnWeek;
    @FXML
    private TableColumn<Task, Integer> comlexityColumnMonth;
    @FXML
    private TableColumn<Task, String> titleColumnMonth;
    @FXML
    private TableColumn<Task, Integer> comlexityColumnYear;
    @FXML
    private TableColumn<Task, String> titleColumnYear;
    private Stage mainStage;

    @FXML
    private void removeSelectionButtonOnAction(){
        taskTableDay.getSelectionModel().clearSelection();
        taskTableWeek.getSelectionModel().clearSelection();
        taskTableMonth.getSelectionModel().clearSelection();
        taskTableYear.getSelectionModel().clearSelection();
    }

    @FXML
    private void moveButtonOnAction(){
        String flagok = new String();
        flagok = comboBox.getValue();
        int tablePositionDay, tablePositionWeek, tablePositionMonth, tablePositionYear;

        tablePositionDay = taskTableWeek.getSelectionModel().getSelectedIndex();
        tablePositionWeek = taskTableWeek.getSelectionModel().getSelectedIndex();
        tablePositionMonth = taskTableWeek.getSelectionModel().getSelectedIndex();
        tablePositionYear = taskTableWeek.getSelectionModel().getSelectedIndex();

        if (tablePositionDay > -1){
            tablePosition = tablePositionDay;
            tablePosition =  taskTableDay.getSelectionModel().getSelectedIndex();
            moveToList(taskDataDay, flagok);
        } else{
            if (tablePositionWeek > -1){
                tablePosition = tablePositionWeek;
                tablePosition =  taskTableWeek.getSelectionModel().getSelectedIndex();
                moveToList(taskDataWeek, flagok);
            } else{
                if (tablePositionMonth > -1){
                    tablePosition = tablePositionMonth;
                    tablePosition =  taskTableMonth.getSelectionModel().getSelectedIndex();
                    moveToList(taskDataMonth, flagok);
                } else{
                    if (tablePositionYear > -1){
                        tablePosition = tablePositionYear;
                        tablePosition =  taskTableYear.getSelectionModel().getSelectedIndex();
                        moveToList(taskDataYear, flagok);
                    }
                }
            }
        }
            }

    /**
     * Перенос данных в другой список
     * удаление из текущего
     * @param taskData
     * @param flagochek
     */
    private void moveToList(ObservableList<Task> taskData, String flagochek){
        switch (flagochek){
            case ("Day"):
                taskDataDay.add(taskData.get(tablePosition));
                break;
            case ("Week"):
                taskDataWeek.add(taskData.get(tablePosition));
                break;
            case ("Month"):
                taskDataMonth.add(taskData.get(tablePosition));
                break;
            case ("Year"):
                taskDataYear.add(taskData.get(tablePosition));
                break;
        }
        taskData.remove(tablePosition);
        writeInFile();
    }

    /**
     * Активна кнопка Delete
     */
    @FXML
    private void deleteButtonOnAction() {
        int tablePositionDay, tablePositionWeek, tablePositionMonth, tablePositionYear;
        tablePositionDay =  taskTableDay.getSelectionModel().getSelectedIndex();
        tablePositionWeek =  taskTableWeek.getSelectionModel().getSelectedIndex();
        tablePositionMonth =  taskTableMonth.getSelectionModel().getSelectedIndex();
        tablePositionYear =  taskTableYear.getSelectionModel().getSelectedIndex();
        if (tablePositionDay > -1){
            tablePosition = tablePositionDay;
            taskDataDay.remove(tablePosition);
        } else{
            if (tablePositionWeek > -1){
                tablePosition = tablePositionWeek;
                taskDataWeek.remove(tablePosition);
            } else{
                if (tablePositionMonth > -1){
                    tablePosition = tablePositionMonth;
                    taskDataMonth.remove(tablePosition);
                } else{
                    if (tablePositionYear > -1){
                        tablePosition = tablePositionYear;
                        taskDataYear.remove(tablePosition);
                    }
                }
            }
        }
        writeInFile();
    }

    /**
     * Активна кнопка Upgreat
     */
    @FXML
    private void upgreatOnCAction() {
        //Обновление счета
        scoreTextLebel.setText(String.valueOf(score));
        if (flagScopOut){
            writeInFile("Score.txt");
            flagScopOut = false;
        }

    }

    /**
     * Чтение списка задач из файла
     * @param fileName
     * @param taskData
     */
    private void readFromFile(String fileName, ObservableList<Task> taskData){
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
                        taskData.add(new Task(number, name));
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
     * Чтение счета из файла
     * @param fileName
     */
    private void readFromFile(String fileName){
        try {
            FileInputStream fstream = new FileInputStream("Score.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine = new String();
            String name = new String();
            boolean f = true;
            while ((strLine = br.readLine()) != null) {
                if ((strLine.equals(""))) { }
                else {
                    score = Integer.parseInt(strLine);
                }
            }
            fstream.close();
        } catch (IOException e) {
            System.out.println("Error: read error");
        }
    }

    /**
     * Вывод списка задач в файл
     * @param fileName
     * @param taskData
     */
    private void writeInFile(String fileName, ObservableList<Task> taskData){
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (Task task : taskData) {
                String name = task.getTitle();
                int complexity = task.getComplexity();
                writer.write(complexity + System.getProperty("line.separator"));
                writer.write(name + System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: write error");
        }
    }

    /**
     * Вывод счета в файл
     * @param fileName
     */
    private void writeInFile(String fileName){
        try (FileWriter writer = new FileWriter(fileName, false)) {
            String tempString = new String();
            tempString = String.valueOf(score);
            writer.write(tempString);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: write error");
        }
    }

    private void writeInFile(){
        writeInFile("TaskDay.txt", taskDataDay);
        writeInFile("TaskWeek.txt", taskDataWeek);
        writeInFile("TaskMonth.txt", taskDataMonth);
        writeInFile("TaskYear.txt", taskDataYear);
    }


    /**
     * Открытие окна магазина.
     * (Родительское окно - текущее)
     * Подключение контроллера второго окна.
     */
    @FXML
    private void handleButtonAction() {
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource("SecondWindow.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Store");
            Scene scene = new Scene(root1, 1300, 900);
            stage.setScene(scene);
            //Новое окно - дочернее
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);
            //Текущее окно недоступно
            stage.setResizable(false);
            stage.show();
            //Подключение контроллера второго окна
            SecondWindowController ctrl = fxmlLoader.getController();
            //Передача второму контроллеру ссылку на текущий контроллер
            ctrl.setController(this);
            ((SecondWindowController) fxmlLoader.getController()).setController(this);
        } catch (Exception e) {
            System.out.println("Error: can't load new Window");
        }
    }

    /**
     * Назначение главного окна
     * @param mainStage
     */
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


    /**
     * Добавление новой задачи
     * @param task Объект задачи
     */
    private void addTask(Task task) {
        //Обновление списка и таблицы
        if (flag == 1) {
            taskDataDay.add(task);
            taskTableDay.refresh();
            writeInFile("TaskDay.txt", taskDataDay);
        }
        if (flag == 3) {
            taskDataMonth.add(task);
            taskTableMonth.refresh();
            writeInFile("TaskMonth.txt", taskDataMonth);
        }
        if (flag == 2) {
            taskDataWeek.add(task);
            taskTableWeek.refresh();
            writeInFile("TaskWeek.txt", taskDataWeek);
        }
        if (flag == 4) {
            taskDataYear.add(task);
            taskTableYear.refresh();
            writeInFile("TaskYear.txt", taskDataYear);
        }
    }


    /**
     * Открытие окна ввода
     * для создания новой задачи
     */
    private void enterWindowShow() {
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource("Enter.fxml"));
            Parent root1 = fxmlLoader.load();
            EnterController ctrl = fxmlLoader.getController();
            Stage stage = new StageWithReturn(); // Добавили Stage функционал возврата результата из окна
            stage.setTitle("Enter");
            stage.setScene(new Scene(root1, 300, 260));

            // Новое окно - дочернее
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);
            Task task = (Task) ((StageWithReturn) stage).showAndReturn(ctrl); // Получаем из второй формы объект задания
            addTask(task); // Добавляем задачи
        } catch (Exception e) {
            System.out.println("Error: can't load new Window");
        }
    }

    @FXML
    private void addDayTaskOnAction() {
        flag = 1;
        enterWindowShow();
    }

    @FXML
    private void addWeekTaskOnAction() {
        flag = 2;
        enterWindowShow();
    }

    @FXML
    private void addMonthTaskOnAction() {
        flag = 3;
        enterWindowShow();
    }

    @FXML
    private void addYearTaskOnAction() {
        flag = 4;
        enterWindowShow();
    }

    /**
     * Конструктор
     * Dызывается раньше метода initialize().
     */
    public Controller() {
        readFromFile("Score.txt");
        readFromFile("TaskDay.txt", taskDataDay);
        readFromFile("TaskWeek.txt", taskDataWeek);
        readFromFile("TaskMonth.txt", taskDataMonth);
        readFromFile("TaskYear.txt", taskDataYear);
    }

    /**Инициализация класса-контроллера.
     * Метод вызывается автоматичнески аосле загрузки fxml-файла
     */
    @FXML
    private void initialize() {
        flagScopOut = false;
        //Combo-box
        String stringDay = "Day";
        String stringWeek = "Week";
        String stringMonth = "Month";
        String stringYear = "Year";
        comboBox.getItems().addAll(stringDay, stringWeek, stringMonth, stringYear);
        scoreTextLebel.setText(String.valueOf(score));

        // Инициализация таблиц
        comlexityColumnDay.setCellValueFactory(cellData -> cellData.getValue().complexityProperty().asObject());
        titleColumnDay.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        comlexityColumnWeek.setCellValueFactory(cellData -> cellData.getValue().complexityProperty().asObject());
        titleColumnWeek.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        comlexityColumnMonth.setCellValueFactory(cellData -> cellData.getValue().complexityProperty().asObject());
        titleColumnMonth.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        comlexityColumnYear.setCellValueFactory(cellData -> cellData.getValue().complexityProperty().asObject());
        titleColumnYear.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        //Создание Слушателей
        AddListeners();

        //Заполнение списков
        setTaskDataDay();
        setTaskDataWeek();
        setTaskDataMonth();
        setTaskDataYear();
        flagScopOut = true;
    }

    /**
     * Создание слушателей изменений
     */
    public void AddListeners(){
        taskDataDay.addListener(new ListChangeListener<Task>() {
            @Override
            public void onChanged(Change<? extends Task> c) {
                taskTableDay.setItems(taskDataDay);
                taskTableDay.refresh();
            }
        });
        taskDataWeek.addListener(new ListChangeListener<Task>() {
            @Override
            public void onChanged(Change<? extends Task> c) {
                taskTableWeek.setItems(taskDataWeek);
                taskTableWeek.refresh();
            }
        });
        taskDataMonth.addListener(new ListChangeListener<Task>() {
            @Override
            public void onChanged(Change<? extends Task> c) {
                taskTableMonth.setItems(taskDataMonth);
                taskTableMonth.refresh();
            }
        });
        taskDataYear.addListener(new ListChangeListener<Task>() {
            @Override
            public void onChanged(Change<? extends Task> c) {
                taskTableYear.setItems(taskDataYear);
                taskTableYear.refresh();
            }
        });
    }

    public void setTaskDataDay() {
        taskTableDay.setItems(getTaskDataDay());
    }

    public void setTaskDataWeek() {
        taskTableWeek.setItems(getTaskDataWeek());
    }

    public void setTaskDataMonth() {
        taskTableMonth.setItems(getTaskDataMonth());
    }

    public void setTaskDataYear() {
        taskTableYear.setItems(getTaskDataYear());
    }



    public ObservableList<Task> getTaskDataDay() {
        return taskDataDay;
    }

    public ObservableList<Task> getTaskDataWeek() {
        return taskDataWeek;
    }

    public ObservableList<Task> getTaskDataMonth() {
        return taskDataMonth;
    }

    public ObservableList<Task> getTaskDataYear() {
        return taskDataYear;
    }



    public void setScore(int scorePar) {
        score = scorePar;
        scoreTextLebel.setText(String.valueOf(score));
    }

    public int getScore() {
        return score;
    }

    public Controller(int scorePar) {
        score = scorePar;
    }
}