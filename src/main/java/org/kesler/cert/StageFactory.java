package org.kesler.cert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.kesler.cert.persona.PersonaController;
import org.kesler.cert.persona.PersonaListController;

import java.io.IOException;

public abstract class StageFactory {

    public static enum Stages {
//        MAIN,
        PERSONA,
        PERSONA_LIST,
        CA,
        CERT,
        CERT_RIGHT
    }

//    private static Stage mainStage;
    private static Stage personaListStage;
    private static PersonaListController personaListController;
    private static Stage personaStage;
    private static PersonaController personaController;

    public static void createMainStage(final Stage mainStage) throws Exception{
        Parent root = FXMLLoader.load(StageFactory.class.getResource("/fxml/Main.fxml"));
        mainStage.setTitle("База сертификатов");
        mainStage.setScene(new Scene(root));
    }


    public static Stage createPersonaListStage(Window owner) throws Exception {

        personaListStage = new Stage(StageStyle.UTILITY);

        FXMLLoader loader = new FXMLLoader(StageFactory.class.getResource("/fxml/PersonaList.fxml"));
        Parent root = loader.load();
        personaListController = loader.getController();
        personaListStage.setScene(new Scene(root));
        personaListStage.initOwner(owner);
        personaListStage.setTitle("Сотрудники");


        return personaListStage;
    }

    public static PersonaListController getPersonaListController() {
        return personaListController;
    }

    public static Stage createPersonaStage(Window owner) throws Exception {

        personaStage = new Stage(StageStyle.UTILITY);

        FXMLLoader loader = new FXMLLoader(StageFactory.class.getResource("/fxml/Persona.fxml"));
        Parent root = loader.load();
        personaController = loader.getController();
        personaStage.setScene(new Scene(root));
        personaStage.initOwner(owner);
        personaStage.setTitle("Сотрудник");

        return personaStage;
    }

    public static PersonaController getPersonaController() {
        return personaController;
    }

}
