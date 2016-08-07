package Root.CustomContol;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class CustomLable extends Label{
    private  int value;
    private  String label;

    public CustomLable(String label, int value, Color color,Font font){
        this.label=label;
        this.setTextFill(color);
        setText(label+": "+value);
        setFont(font);


    }

    public String getTextAsString(){
        return label+": "+value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
