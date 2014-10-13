package org.kesler.cert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    public static Stage getStage(Stages type) throws Exception{
        switch (type) {
            case PERSONA_LIST:
                return createPersonaListStage();
            default:
                return null;
        }
    }


    public static void createMainStage(final Stage mainStage) throws Exception{
        Parent root = FXMLLoader.load(StageFactory.class.getResource("/fxml/Main.fxml"));
        mainStage.setTitle("База сертификатов");
        mainStage.setScene(new Scene(root));
    }


    private static Stage createPersonaListStage() throws Exception{
        if (personaListStage==null) {
            personaListStage = new Stage(StageStyle.UTILITY);

            Parent root = FXMLLoader.load(StageFactory.class.getResource("/fxml/PersonaList.fxml"));
            personaListStage.setScene(new Scene(root));
            personaListStage.setTitle("Сотрудники");

        }

        return personaListStage;
    }

}
