package org.kesler.cert.persona;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.kesler.cert.domain.Person;
import org.kesler.cert.domain.Scan;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class PersonaController{

    @FXML
    protected Parent root;

    public enum Result{
        OK,
        CANCEL,
        NONE
    }

    protected Person person;

    private Result result = Result.NONE;

    @FXML protected TextField surNameTextField;
    @FXML protected TextField nameTextField;
    @FXML protected TextField parentNameTextField;
    @FXML protected TextField paspSerTextField;
    @FXML protected TextField paspNumTextField;
    @FXML protected DatePicker paspIssueDatePicker;
    @FXML protected TextArea paspIssueOGVTextArea;
    @FXML protected TextField snilsTextField;
    @FXML protected ListView<Scan> scansListView;

    public void initPersona(Person person) {
        this.person = person;
        updateView();
    }


    @FXML
    protected void handleOkButtonAction(ActionEvent ev) {
        result = Result.OK;
        updatePersona();
        root.getScene().getWindow().hide();
    }

    @FXML
    protected void handleCancelButtonAction(ActionEvent ev) {
        result = Result.CANCEL;
        root.getScene().getWindow().hide();
    }

    private void updateView() {
        surNameTextField.setText(person.getSurName());
        nameTextField.setText(person.getName());
        parentNameTextField.setText(person.getParentName());

        paspSerTextField.setText(person.getPaspSer());
        paspNumTextField.setText(person.getPaspNum());
        Date paspIssueDate = person.getPaspIssueDate();
        paspIssueDatePicker.setValue((paspIssueDate==null?null:paspIssueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        paspIssueOGVTextArea.setText(person.getPaspIssueOGV());

        snilsTextField.setText(person.getSnils());

    }

    private void updatePersona() {
        person.setSurName(surNameTextField.getText());
        person.setName(nameTextField.getText());
        person.setParentName(parentNameTextField.getText());

        person.setPaspSer(paspSerTextField.getText());
        person.setPaspNum(paspNumTextField.getText());
        LocalDate paspIssueLocalDate = paspIssueDatePicker.getValue();
        Date paspIssueDate = paspIssueLocalDate==null?null:Date.from(paspIssueLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        person.setPaspIssueDate(paspIssueDate);
        person.setPaspIssueOGV(paspIssueOGVTextArea.getText());

        person.setSnils(snilsTextField.getText());

    }

    public Result getResult() {
        return result;
    }

}
