package org.kesler.cert.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.kesler.cert.domain.Cert;
import org.kesler.cert.domain.CertRight;
import org.kesler.cert.domain.Person;
import org.kesler.cert.domain.Scan;
import org.kesler.cert.util.FXUtils;


public class PersonController {

    @FXML
    protected Parent root;

    public enum Result{
        OK,
        CANCEL,
        NONE
    }

    protected Person person;

    private ObservableList<Scan> observablePersonScans;
    private ObservableList<CertRight> observablePersonCertRights;
    private ObservableList<Cert> observablePersonCerts;

    private Result result = Result.NONE;

    @FXML protected TextField surNameTextField;
    @FXML protected TextField nameTextField;
    @FXML protected TextField parentNameTextField;
    @FXML protected TextField paspSerTextField;
    @FXML protected TextField paspNumTextField;
    @FXML protected DatePicker paspIssueDatePicker;
    @FXML protected TextArea paspIssueOGVTextArea;
    @FXML protected TextField snilsTextField;
    @FXML protected TextField emailTextField;
    @FXML protected TextField phoneTextField;
    @FXML protected ListView<Scan> scansListView;
    @FXML protected ListView<CertRight> rightsListView;
    @FXML protected ListView<Cert> certsListView;

    // Отрабатывает при загрузке окна
    public void initialize() {
        scansListView.setCellFactory(new Callback<ListView<Scan>, ListCell<Scan>>() {
            @Override
            public ListCell<Scan> call(ListView<Scan> param) {
                return new ScanListCell();
            }
        });

    }

    public void initPerson(Person person) {
        this.person = person;
        updateView();
    }

    // Обработка событий кнопок добавления/удаления сканов

    @FXML
    protected void handleAddScanButtonAction(ActionEvent ev) {

    }

    @FXML
    protected void handleEditScanButtonAction(ActionEvent ev) {

    }

    @FXML
    protected void handleRemoveScanButtonAction(ActionEvent ev) {

    }


    // Обработка событий добавления/удаления прав

    @FXML
    protected void handleAddCertRightButtonAction(ActionEvent ev) {

    }

    @FXML
    protected void handleEditCertRightButtonAction(ActionEvent ev) {

    }

    @FXML
    protected void handleRemoveCertRightButtonAction(ActionEvent ev) {

    }

    // Обработка событий добавления/удаления сертификатов

    @FXML
    protected void handleAddCertButtonAction(ActionEvent ev) {

    }

    @FXML
    protected void handleEditCertButtonAction(ActionEvent ev) {

    }

    @FXML
    protected void handleRemoveCertButtonAction(ActionEvent ev) {

    }


    // Обработчики для основных кнопок окна
    @FXML
    protected void handleOkButtonAction(ActionEvent ev) {
        result = Result.OK;
        updatePerson();
        root.getScene().getWindow().hide();
    }

    @FXML
    protected void handleCancelButtonAction(ActionEvent ev) {
        result = Result.CANCEL;
        root.getScene().getWindow().hide();
    }

    // загружаем сведения о сотруднике в окошко
    private void updateView() {
        surNameTextField.setText(person.getSurName());
        nameTextField.setText(person.getName());
        parentNameTextField.setText(person.getParentName());

        paspSerTextField.setText(person.getPaspSer());
        paspNumTextField.setText(person.getPaspNum());
        paspIssueDatePicker.setValue(FXUtils.dateToLocalDate(person.getPaspIssueDate()));
        paspIssueOGVTextArea.setText(person.getPaspIssueOGV());

        snilsTextField.setText(person.getSnils());

        observablePersonScans = FXCollections.observableArrayList(person.getScans());
        scansListView.setItems(observablePersonScans);

        observablePersonCertRights = FXCollections.observableArrayList(person.getRights());
        rightsListView.setItems(observablePersonCertRights);

        observablePersonCerts = FXCollections.observableArrayList(person.getCerts());
        certsListView.setItems(observablePersonCerts);

    }

    // Считываем обновленные сведения о сотруднике
    private void updatePerson() {
        person.setSurName(surNameTextField.getText());
        person.setName(nameTextField.getText());
        person.setParentName(parentNameTextField.getText());

        person.setPaspSer(paspSerTextField.getText());
        person.setPaspNum(paspNumTextField.getText());
        person.setPaspIssueDate(FXUtils.localDateToDate(paspIssueDatePicker.getValue()));
        person.setPaspIssueOGV(paspIssueOGVTextArea.getText());

        person.setSnils(snilsTextField.getText());

    }

    public Result getResult() {
        return result;
    }

    // Рендеры для списков (как мы формируем строку в списке)
    class ScanListCell extends ListCell<Scan> {
        @Override
        protected void updateItem(Scan item, boolean empty) {
            super.updateItem(item, empty);
            setText(item==null?"": (item.getName()==null?"":item.getName()));
        }
    }


}
