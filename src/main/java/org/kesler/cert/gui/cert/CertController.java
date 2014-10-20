package org.kesler.cert.gui.cert;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import org.kesler.cert.domain.Cert;

public class CertController {
    @FXML protected Parent root;

    private Cert cert;

    @FXML
    protected void initialize() {

    }

    public void initCert(Cert cert) {
        this.cert = cert;
    }



}
