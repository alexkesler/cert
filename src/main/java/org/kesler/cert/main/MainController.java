package org.kesler.cert.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.kesler.cert.StageFactory;
import org.kesler.cert.model.PersonService;
import org.kesler.cert.model.impl.PersonServiceSimpleImpl;

public class MainController {

    @FXML
    protected Parent root;

    private PersonService personService = new PersonServiceSimpleImpl();

    @FXML
    protected void handlePersonaListButtonAction(ActionEvent ev) {

        Stage personaListStage = null;
        try {
            personaListStage = StageFactory.createPersonaListStage(root.getScene().getWindow());

        } catch (Exception e) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Ошибка создания окна")
                    .message(e.getMessage())
                    .showError();
            e.printStackTrace();
            return;
        }

        StageFactory.getPersonListController().setPersonService(personService);
        personaListStage.show();

    }

    @FXML
    protected void handlePersonaButtonAction(ActionEvent ev) {


    }

    @FXML
    protected void handleIssueButtonAction(ActionEvent ev) {

    }
}
