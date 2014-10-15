package org.kesler.cert.gui;

import javafx.fxml.FXML;
import javafx.scene.Parent;

public class AbstractController {

    @FXML protected Parent root;

    public enum Result{
        OK,
        CANCEL,
        NONE
    }

    protected Result result = Result.NONE;

    public Result getResult() {
        return result;
    }
}
