package Robotics2442C;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author jackkates
 */

public class Dialog extends Stage {

    public Dialog(String title, Parent root) {
        this(title, root, 0, 0);
    }

    public Dialog(String title, Parent root, double x, double y) {
        setTitle(title);
        setResizable(false);
        setScene(new Scene(root, x, y));
    }

    public void showDialog() {
        centerOnScreen();
        show();
    }

    public static Dialog show(String title, Parent root, double x, double y) {
        Dialog dialog = new Dialog(title, root, x, y);
        dialog.showDialog();
        return dialog;
    }

}
