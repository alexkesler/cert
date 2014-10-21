package org.kesler.cert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.kesler.cert.gui.cert.CertController;
import org.kesler.cert.gui.certright.CertRightController;
import org.kesler.cert.gui.person.PersonController;
import org.kesler.cert.gui.person.PersonListController;
import org.kesler.cert.gui.scan.ScanController;

public abstract class StageFactory {



//    private static Stage mainStage;
    private static Stage personaListStage;
    private static Stage personaStage;
    private static Stage scanStage;
    private static Stage certStage;
    private static Stage certRightStage;
    private static PersonListController personListController;
    private static PersonController personController;
    private static ScanController scanController;
    private static CertController certController;
    private static CertRightController certRightController;

    public static void createMainStage(final Stage mainStage) throws Exception{
        Parent root = FXMLLoader.load(StageFactory.class.getResource("/fxml/Main.fxml"));
        mainStage.setTitle("База сертификатов");
        mainStage.setScene(new Scene(root));
    }


    public static Stage createPersonListStage(Window owner) throws Exception {

        personaListStage = new Stage(StageStyle.UTILITY);

        FXMLLoader loader = new FXMLLoader(StageFactory.class.getResource("/fxml/PersonList.fxml"));
        Parent root = loader.load();
        personListController = loader.getController();
        personaListStage.setScene(new Scene(root));
        personaListStage.initOwner(owner);
        personaListStage.setTitle("Сотрудники");


        return personaListStage;
    }

    public static Stage createPersonStage(Window owner) throws Exception {

        personaStage = new Stage(StageStyle.UTILITY);

        FXMLLoader loader = new FXMLLoader(StageFactory.class.getResource("/fxml/Person.fxml"));
        Parent root = loader.load();
        personController = loader.getController();
        personaStage.setScene(new Scene(root));
        personaStage.initOwner(owner);
        personaStage.setTitle("Сотрудник");

        return personaStage;
    }

    public static Stage createScanStage(Window owner) throws Exception {

        scanStage = new Stage(StageStyle.UTILITY);

        FXMLLoader loader = new FXMLLoader(StageFactory.class.getResource("/fxml/Scan.fxml"));
        Parent root = loader.load();
        scanController = loader.getController();
        scanStage.setScene(new Scene(root));
        scanStage.initOwner(owner);
        scanStage.setTitle("Скан документа");

        return scanStage;
    }

    public static Stage createCertStage(Window owner) throws Exception {

        certStage = new Stage(StageStyle.UTILITY);

        FXMLLoader loader = new FXMLLoader(StageFactory.class.getResource("/fxml/Cert.fxml"));
        Parent root = loader.load();
        certController = loader.getController();
        certStage.setScene(new Scene(root));
        certStage.initOwner(owner);
        certStage.setTitle("Сертификат");

        return certStage;
    }

    public static Stage createCertRightStage(Window owner) throws Exception {

        certRightStage = new Stage(StageStyle.UTILITY);

        FXMLLoader loader = new FXMLLoader(StageFactory.class.getResource("/fxml/CertRight.fxml"));
        Parent root = loader.load();
        certRightController = loader.getController();
        certRightStage.setScene(new Scene(root));
        certRightStage.initOwner(owner);
        certRightStage.setTitle("Права владельца");

        return certRightStage;
    }

    public static PersonListController getPersonListController() { return personListController; }
    public static PersonController getPersonController() { return personController; }
    public static ScanController getScanController() { return scanController; }
    public static CertController getCertController() {return certController; }
    public static CertRightController getCertRightController() {return certRightController; }

}
