package Root.CustomContol;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomButton extends Button {
    public CustomButton(String text) {
        super.setText(text);
        super.setStyle("-fx-border-color: Black, Yellow;\n" +
                "    -fx-border-width: 1, 1;\n" +
                "    -fx-border-style: solid;\n" +
                "    -fx-border-radius: 0, 0;\n" +
                "    -fx-border-insets: 1 1 1 1, 0;");

        super.setTextFill(Color.BLUE);
        this.setFont(Font.font("Harrington", 15));
    }
}
