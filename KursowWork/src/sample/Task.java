package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private  IntegerProperty complexity;
    private  StringProperty title;

    /**
     * Конструктор по умолчанию.

     */
    public Task() {
        this(null,null);
    }

    /**
     * Конструктор норм
     * @param complexity
     * @param title
     */
    public Task(Integer complexity, String title) {
        this.complexity = new SimpleIntegerProperty(complexity);
        this.title = new SimpleStringProperty(title);
    }

    public String getTitle(){
        return title.get();
    }

    public void setTitle(String title)
    {
        this.title.set(title);
    }

    public StringProperty titleProperty(){
        return title;
    }

    public int getComplexity(){
        return complexity.get();
    }

    public void setComplexity(int complexity) {
        this.complexity.set(complexity);
    }

    public IntegerProperty complexityProperty(){
        return complexity;
    }

}
