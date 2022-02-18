package yoda;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;

/**
 * Represents the dialog boxes in the GUI.
 */
public class DialogBox extends HBox {

    @FXML private Label dialog;
    @FXML private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Generates the dialog box for the
     * user input in the GUI.
     * @param text Text to display in label.
     * @param img  User's avatar.
     * @return DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Generates the dialog box for the bot's
     * response to the user input.
     * @param text Text to display in label.
     * @param img Bot's avatar.
     * @return
     */
    public static DialogBox getYodaDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
