package org.kesler.cert.gui.certright;

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
import org.kesler.cert.domain.CertRight;
import org.kesler.cert.gui.AbstractController;
import org.kesler.cert.model.CertRightService;
import org.kesler.cert.util.FXUtils;


public class CertRightListController {
    private Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private CertRightService certRightService;
    private ObservableList<CertRight> observableCertRights;

    @FXML protected Parent root;
    @FXML protected ListView<CertRight> certRightListView;

    @FXML
    protected void initialize() {
        certRightListView.setCellFactory(new Callback<ListView<CertRight>, ListCell<CertRight>>() {
            @Override
            public ListCell<CertRight> call(ListView<CertRight> param) {
                return new CertRightListCell();
            }
        });
    }

    public void setCertRightService(CertRightService certRightService) {
        this.certRightService = certRightService;
        observableCertRights = FXCollections.observableArrayList(certRightService.getAllCertRights());
        certRightListView.setItems(observableCertRights);
    }


    // Обработчики кнопок управления списком сотрудников
    @FXML
    protected void handleAddCertRightButtonAction(ActionEvent ev) {
        addCertRight();
    }

    @FXML
    protected void handleEditCertRightButtonAction(ActionEvent ev) {
        editCertRight();
    }

    @FXML
    protected void handleCertRightListViewMouseClick(MouseEvent ev) {
        if (ev.getClickCount()==2) {
            editCertRight();
        }
    }

    @FXML
    protected void handleRemoveCertRightButtonAction(ActionEvent ev) {
        removeCertRight();
    }


    // Обработчики основных кнопок окна
    @FXML
    protected void handleOkButtonAction(ActionEvent ev) {
        log.info("Hide view");
        root.getScene().getWindow().hide();
    }




    // Методы управления списком сотрудников

    private void addCertRight() {
        log.info("Opening add CertRight dialog");
        Stage stage = null;
        try {
            stage = StageFactory.createCertRightStage(root.getScene().getWindow());

        } catch (Exception e) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Ошибка создания окна")
                    .message(e.getMessage())
                    .showException(e);
            return;
        }

        CertRight newCertRight = new CertRight();
        StageFactory.getCertRightController().initCertRight(newCertRight);
        stage.showAndWait();
        if (StageFactory.getCertRightController().getResult() == AbstractController.Result.OK) {
            log.info("Saving CertRight: " + newCertRight.getCode());
            observableCertRights.add(newCertRight);
            certRightService.addCertRight(newCertRight);
            certRightListView.getSelectionModel().select(observableCertRights.indexOf(newCertRight));
        }

    }

    private void editCertRight() {
        int selectedIndex = certRightListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex<0) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Внимание")
                    .message("Ничего не выбрано")
                    .showWarning();
            return;
        }
        CertRight selectedCertRight = observableCertRights.get(selectedIndex);

        Stage stage = null;
        try {
            stage = StageFactory.createCertRightStage(root.getScene().getWindow());

        } catch (Exception e) {
            Dialogs.create()
                    .owner(root.getScene().getWindow())
                    .title("Ошибка создания окна")
                    .message(e.getMessage())
                    .showException(e);

            return;
        }

        StageFactory.getCertRightController().initCertRight(selectedCertRight);
        stage.showAndWait();
        if (StageFactory.getCertRightController().getResult() == CertRightController.Result.OK) {
            certRightService.updateCertRight(selectedCertRight);
            FXUtils.triggerUpdateListView(certRightListView, selectedCertRight, selectedIndex);
            certRightListView.getSelectionModel().select(selectedIndex);
        }

    }


    private void removeCertRight() {
        CertRight selectedCertRight = certRightListView.getSelectionModel().getSelectedItem();
        if (selectedCertRight ==null) {
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
                .message("Удалить выбранные права: " + selectedCertRight.getCode())
                .showConfirm();
        if (response == Dialog.ACTION_YES) {
            observableCertRights.remove(selectedCertRight);
            certRightService.removeCertRight(selectedCertRight);
        }
    }


    // Вспомогательные классы для списка сотрудников
    class CertRightListCell extends ListCell<CertRight> {
        @Override
        protected void updateItem(CertRight item, boolean empty) {
            super.updateItem(item, empty);
            setText(item==null?"":(item.getCode()==null?"":item.getCode()));
        }
    }

 }
