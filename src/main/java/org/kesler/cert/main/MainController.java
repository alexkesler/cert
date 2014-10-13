package org.kesler.cert.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.kesler.cert.StageFactory;

public class MainController {


    @FXML
    protected void handlePersonaListButtonAction(ActionEvent ev) {

        try {
            Stage personaListStage = StageFactory.getStage(StageFactory.Stages.PERSONA_LIST);
            personaListStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
