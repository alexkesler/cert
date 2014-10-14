package org.kesler.cert.persona;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.controlsfx.dialog.Dialogs;
import org.kesler.cert.StageFactory;
import org.kesler.cert.domain.Person;
import org.kesler.cert.model.PersonService;

import java.util.ArrayList;
import java.util.List;

public class PersonaListController {
    private Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private PersonService personService;
    private ObservableList<Person> observablePersons;

    @FXML protected Parent root;
    @FXML protected ListView<Person> personsListView;

    public void initialize() {
        personsListView.setCellFactory(new Callback<ListView<Person>, ListCell<Person>>() {
            @Override
            public ListCell<Person> call(ListView<Person> param) {
                return new PersonaListCell();
            }
        });
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
        List<Person> persons = new ArrayList<Person>(personService.getAllPersonas());
        observablePersons = FXCollections.observableList(persons);
        personsListView.setItems(observablePersons);
    }


    @FXML
    protected void handleOkButtonAction(ActionEvent ev) {
        log.info("Hide view");
        root.getScene().getWindow().hide();
    }

    @FXML
    protected void handleAddPersonaButtonAction(ActionEvent ev) {
        log.info("Opening add persona dialog");
        Stage stage = null;
        try {
            stage = StageFactory.createPersonaStage(root.getScene().getWindow());

        } catch (Exception e) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Ошибка создания окна")
                    .message(e.getMessage())
                    .showError();
            e.printStackTrace();
            return;
        }

        Person newPerson = new Person();
        StageFactory.getPersonaController().initPersona(newPerson);
        stage.showAndWait();
        if (StageFactory.getPersonaController().getResult() == PersonaController.Result.OK) {
            observablePersons.add(newPerson);
            personService.addPersona(newPerson);
        }

    }

    @FXML
    protected void handleRemovePersonaButtonAction(ActionEvent ev) {

    }

    @FXML
    protected void handleEditPersonaButtonAction(ActionEvent ev) {
        Person selectedPerson = personsListView.getSelectionModel().getSelectedItem();
        int selectedIndex = personsListView.getSelectionModel().getSelectedIndex();
        if (selectedPerson ==null) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Внимание")
                    .message("Ничего не выбрано")
                    .showWarning();
            return;
        }

        Stage stage = null;
        try {
            stage = StageFactory.createPersonaStage(root.getScene().getWindow());

        } catch (Exception e) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Ошибка создания окна")
                    .message(e.getMessage())
                    .showError();
            e.printStackTrace();
            return;
        }

        StageFactory.getPersonaController().initPersona(selectedPerson);
        stage.showAndWait();
        if (StageFactory.getPersonaController().getResult() == PersonaController.Result.OK) {
            personService.updatePersona(selectedPerson);
            triggerUpdate(personsListView,selectedPerson,selectedIndex);
            personsListView.getSelectionModel().select(selectedIndex);
        }

    }

    class PersonaListCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person item, boolean empty) {
            super.updateItem(item, empty);
            setText(item==null?"":
                    (item.getSurName()==null?"":(item.getSurName()+" ")) +
                    (item.getName()==null?"":(item.getName() + " ")) +
                    (item.getParentName()==null?"":item.getParentName()));
        }
    }

    public static <T> void triggerUpdate(ListView<T> listView, T newValue, int i) {
        EventType<? extends ListView.EditEvent<T>> type = ListView.<T>editCommitEvent();
        Event event = new ListView.EditEvent<T>(listView, type, newValue, i);
        listView.fireEvent(event);
    }
}
