package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SortingRectangles extends Rectangle{//Method to add our own unique aspects to each rectangle
    private Color color;
    private Text text;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Text getText(){
        return text;
    }

    public void setText(Text txt){
        text=txt;
    }

    @Override
    public String toString() {
        return "Xpos: "+getX()+" YPos: "+getY()+" Width: "+getWidth()+" Height: "+getHeight();
    }
}
