package org.kesler.cert.gui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.kesler.cert.StageFactory;
import org.kesler.cert.model.CertRightService;
import org.kesler.cert.model.PersonService;
import org.kesler.cert.model.impl.CertRightServiceSimpleImpl;
import org.kesler.cert.model.impl.PersonServiceSimpleImpl;

public class MainController {

    @FXML
    protected Parent root;

    private PersonService personService = new PersonServiceSimpleImpl();
    private CertRightService certRightService = new CertRightServiceSimpleImpl();

    @FXML
    protected void handlePersonListButtonAction(ActionEvent ev) {

        Stage personListStage = null;
        try {
            personListStage = StageFactory.createPersonListStage(root.getScene().getWindow());

        } catch (Exception e) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Ошибка создания окна")
                    .message(e.getMessage())
                    .showException(e);
            return;
        }

        StageFactory.getPersonListController().setPersonService(personService);
        personListStage.show();

    }

    @FXML
    protected void handleCertRightListMenuItem(ActionEvent ev) {
        Stage certRightListStage = null;
        try {
            certRightListStage = StageFactory.createCertRightListStage(root.getScene().getWindow());

        } catch (Exception e) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Ошибка создания окна")
                    .message(e.getMessage())
                    .showException(e);
            return;
        }

        StageFactory.getCertRightListController().setCertRightService(certRightService);
        certRightListStage.show();

    }

    @FXML
    protected void handlePersonButtonAction(ActionEvent ev) {


    }

    @FXML
    protected void handleIssueButtonAction(ActionEvent ev) {

    }
}
