package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Promotion {
    private IntegerProperty cost;
    private StringProperty title;

    /**
     * Конструктор по умолчанию.

     */
    public Promotion() {
        this(null,null);
    }

    /**
     * Конструктор норм
     * @param cost
     * @param title
     */
    public Promotion(Integer cost, String title) {
        this.cost = new SimpleIntegerProperty(cost);
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

    public int getCost(){
        return cost.get();
    }

    public void setCost(int cost) {
        this.cost.set(cost);
    }

    public IntegerProperty costProperty(){
        return cost;
    }

}
