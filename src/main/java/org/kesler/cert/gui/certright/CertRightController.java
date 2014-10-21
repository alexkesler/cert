package org.kesler.cert.gui.certright;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import org.kesler.cert.domain.CertRight;
import org.kesler.cert.gui.AbstractController;

public class CertRightController extends AbstractController{

    private CertRight certRight;

    @FXML
    protected TextField codeTextField;
    @FXML protected TextArea  descTextArea;

    @FXML
    protected void initialize() {

    }

    public void initCertRight(CertRight certRight) {
        this.certRight = certRight;
        updateView();
    }

    private void updateView() {
        codeTextField.setText(certRight.getCode());
        descTextArea.setText(certRight.getDesc());
    }

    private void updateCertRight() {
        certRight.setCode(codeTextField.getText());
        certRight.setDesc(descTextArea.getText());
    }

    @FXML
    protected void handleOkButtonAction(ActionEvent ev) {
        updateCertRight();
        result = Result.OK;
        root.getScene().getWindow().hide();

    }

    @FXML
    protected void handleCancelButtonAction(ActionEvent ev) {
        Action response = Dialogs.create()
                .owner(root.getScene().getWindow())
                .title("Внимание")
                .message("Закрыть без сохранения?")
                .showConfirm();
        if (response == Dialog.ACTION_YES) {
            result = Result.CANCEL;
            root.getScene().getWindow().hide();
        }
    }

}
