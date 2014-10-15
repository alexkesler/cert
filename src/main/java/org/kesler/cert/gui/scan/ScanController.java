package org.kesler.cert.gui.scan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import org.kesler.cert.domain.Scan;
import org.kesler.cert.gui.AbstractController;
import org.kesler.cert.util.FXUtils;

import java.io.File;

public class ScanController extends AbstractController{

    @FXML protected TextArea nameTextArea;
    @FXML protected TextField fileNameTextField;

    private Scan scan;

    public void initialize() {

    }

    public void initScan(Scan scan) {
        this.scan = scan;
        updateView();
    }

    @FXML
    protected void handleSelectFileButtonAction(ActionEvent ev) {
        selectScan();
    }

    @FXML
    protected void handleOkButtonAction(ActionEvent ev) {
        updateScan();
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

    private void updateView() {
        nameTextArea.setText(scan.getName());
        fileNameTextField.setText(scan.getFileName());
    }

    private void updateScan() {
        scan.setName(nameTextArea.getText());
        scan.setFileName(fileNameTextField.getText());
    }

    private void selectScan() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (file!=null) {
//            if (!file.getParentFile().getPath().equals(FXUtils.getJarPath()+"scans" + File.separator)) {
//                file.getName()
//            }
            fileNameTextField.setText(file.getPath());
        }
    }

}
