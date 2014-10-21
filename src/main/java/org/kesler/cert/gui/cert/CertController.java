package org.kesler.cert.gui.cert;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.kesler.cert.domain.Cert;
import org.kesler.cert.domain.CertRight;
import org.kesler.cert.util.FXUtils;

public class CertController {
    @FXML protected Parent root;

    @FXML protected TextField caTextField;
    @FXML protected TextField personTextField;
    @FXML protected DatePicker issueDatePicker;
    @FXML protected DatePicker expireDatePicker;
    @FXML protected CheckBox activeCheckBox;

    private Cert cert;
    private ObservableList<CertRight> observableCertRights;

    @FXML
    protected void initialize() {

    }

    public void initCert(Cert cert) {
        this.cert = cert;
    }

    private void updateView() {
        caTextField.setText(cert.getCa()==null?"Не опр":cert.getCa().getName());
        personTextField.setText(cert.getPerson()==null?"Не опр":cert.getPerson().getFIO());
        issueDatePicker.setValue(FXUtils.dateToLocalDate(cert.getIssueDate()));
        expireDatePicker.setValue(FXUtils.dateToLocalDate(cert.getExpireDate()));
        activeCheckBox.setSelected(cert.isActive());
    }

    private void updateCert() {

    }



}
