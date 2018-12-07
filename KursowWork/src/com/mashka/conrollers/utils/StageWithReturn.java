package com.mashka.conrollers.utils;

import javafx.stage.Stage;

/**
 * Объект Stage с возможностью возврата произвольного объекта
 */
public class StageWithReturn extends Stage {
    public Object showAndReturn(Returnable controll) {
        super.showAndWait();
        return controll.getReturn();
    }
}
