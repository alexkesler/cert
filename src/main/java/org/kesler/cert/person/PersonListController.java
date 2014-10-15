package org.kesler.cert.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import org.kesler.cert.StageFactory;
import org.kesler.cert.domain.Person;
import org.kesler.cert.model.PersonService;
import org.kesler.cert.util.FXUtils;


public class PersonListController {
    private Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private PersonService personService;
    private ObservableList<Person> observablePersons;

    @FXML protected Parent root;
    @FXML protected ListView<Person> personsListView;

    public void initialize() {
        personsListView.setCellFactory(new Callback<ListView<Person>, ListCell<Person>>() {
            @Override
            public ListCell<Person> call(ListView<Person> param) {
                return new PersonListCell();
            }
        });
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
        observablePersons = FXCollections.observableArrayList(personService.getAllPersons());
        personsListView.setItems(observablePersons);
    }


    // Обработчики кнопок управления списком сотрудников
    @FXML
    protected void handleAddPersonButtonAction(ActionEvent ev) {
        addPerson();
    }

    @FXML
    protected void handleEditPersonButtonAction(ActionEvent ev) {
        editPerson();
    }

    @FXML
    protected void handlePersonsListViewMouseClick(MouseEvent ev) {
        if (ev.getClickCount()==2) {
            editPerson();
        }
    }

    @FXML
    protected void handleRemovePersonButtonAction(ActionEvent ev) {
        removePerson();
    }


    // Обработчики основных кнопок окна
    @FXML
    protected void handleOkButtonAction(ActionEvent ev) {
        log.info("Hide view");
        root.getScene().getWindow().hide();
    }




    // Методы управления списком сотрудников

    private void addPerson() {
        log.info("Opening add person dialog");
        Stage stage = null;
        try {
            stage = StageFactory.createPersonaStage(root.getScene().getWindow());

        } catch (Exception e) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Ошибка создания окна")
                    .message(e.getMessage())
                    .showException(e);
            return;
        }

        Person newPerson = new Person();
        StageFactory.getPersonController().initPerson(newPerson);
        stage.showAndWait();
        if (StageFactory.getPersonController().getResult() == PersonController.Result.OK) {
            observablePersons.add(newPerson);
            personService.addPerson(newPerson);
            personsListView.getSelectionModel().select(observablePersons.indexOf(newPerson));
        }

    }

    private void editPerson() {
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
                    .showException(e);

            return;
        }

        StageFactory.getPersonController().initPerson(selectedPerson);
        stage.showAndWait();
        if (StageFactory.getPersonController().getResult() == PersonController.Result.OK) {
            personService.updatePerson(selectedPerson);
            FXUtils.triggerUpdateListView(personsListView, selectedPerson, selectedIndex);
            personsListView.getSelectionModel().select(selectedIndex);
        }

    }


    private void removePerson() {
        Person selectedPerson = personsListView.getSelectionModel().getSelectedItem();
        if (selectedPerson ==null) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Внимание")
                    .message("Ничего не выбрано")
                    .showWarning();
            return;
        }

        Action response = Dialogs.create()
                .owner(root.getScene().getWindow())
                .title("Подтверждение")
                .message("Удалить данные о сотруднике: " + selectedPerson.getSurName())
                .showConfirm();
        if (response == Dialog.ACTION_YES) {
            observablePersons.remove(selectedPerson);
            personService.removePerson(selectedPerson);
        }
    }


    // Вспомогательные классы для списка сотрудников
    class PersonListCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person item, boolean empty) {
            super.updateItem(item, empty);
            setText(item==null?"":
                    (item.getSurName()==null?"":(item.getSurName()+" ")) +
                    (item.getName()==null?"":(item.getName() + " ")) +
                    (item.getParentName()==null?"":item.getParentName()));
        }
    }

 }
