package org.kesler.cert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.kesler.cert.person.PersonController;
import org.kesler.cert.person.PersonListController;

public abstract class StageFactory {



//    private static Stage mainStage;
    private static Stage personaListStage;
    private static PersonListController personListController;
    private static Stage personaStage;
    private static PersonController personController;

    public static void createMainStage(final Stage mainStage) throws Exception{
        Parent root = FXMLLoader.load(StageFactory.class.getResource("/fxml/Main.fxml"));
        mainStage.setTitle("База сертификатов");
        mainStage.setScene(new Scene(root));
    }


    public static Stage createPersonaListStage(Window owner) throws Exception {

        personaListStage = new Stage(StageStyle.UTILITY);

        FXMLLoader loader = new FXMLLoader(StageFactory.class.getResource("/fxml/PersonList.fxml"));
        Parent root = loader.load();
        personListController = loader.getController();
        personaListStage.setScene(new Scene(root));
        personaListStage.initOwner(owner);
        personaListStage.setTitle("Сотрудники");


        return personaListStage;
    }

    public static PersonListController getPersonListController() {
        return personListController;
    }

    public static Stage createPersonaStage(Window owner) throws Exception {

        personaStage = new Stage(StageStyle.UTILITY);

        FXMLLoader loader = new FXMLLoader(StageFactory.class.getResource("/fxml/Person.fxml"));
        Parent root = loader.load();
        personController = loader.getController();
        personaStage.setScene(new Scene(root));
        personaStage.initOwner(owner);
        personaStage.setTitle("Сотрудник");

        return personaStage;
    }

    public static PersonController getPersonController() {
        return personController;
    }

}
