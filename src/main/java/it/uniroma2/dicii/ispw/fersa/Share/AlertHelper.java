package it.uniroma2.dicii.ispw.fersa.Share;

import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import javafx.stage.Window;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;

public class AlertHelper extends Alert {

    public AlertHelper(AlertType alertType) {
        super(alertType);
    }

    public static void showAlert(AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }

    public static Boolean showConfirmationAlert(String message, Window ower) {
        AlertHelper alertHelper = new AlertHelper(AlertType.CONFIRMATION);
        alertHelper.setTitle("Confirm");
        alertHelper.setContentText(message);
        alertHelper.initOwner(ower);
        Optional<ButtonType> result = alertHelper.showAndWait();
        if (result.get() == ButtonType.CANCEL) {
            return false;
        }
        return true;
    }
}

