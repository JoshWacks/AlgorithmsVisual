//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sample;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class VisualMethods {
    private static int numRectangles = 20;
    private static double rectangleWidth;
    private static Canvas canvas;
    private static GraphicsContext gc;
    private static ArrayList<SortingRectangles> rectanglesArrayList = new ArrayList();

    public VisualMethods(double width, double height) {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        rectangleWidth = width / (double)numRectangles;
        gc.setLineWidth(1.0D);
    }

    public static Canvas getCanvas() {
        return canvas;
    }

    public static void setCanvas(Canvas canvas) {
        VisualMethods.canvas = canvas;
    }

    public static GraphicsContext getGc() {
        return gc;
    }

    public static void setGc(GraphicsContext gc) {
        VisualMethods.gc = gc;
    }

    public static ArrayList<SortingRectangles> getRectangles() {
        return rectanglesArrayList;
    }

    public static void setRectangles(ArrayList<SortingRectangles> rectangles) {
        rectanglesArrayList = rectangles;
    }

    public static int getNumRectangles() {
        return numRectangles;
    }

    public static void setNumRectangles(int numRectangles) {
        VisualMethods.numRectangles = numRectangles;
    }

    public static double getRectangleWidth() {
        return rectangleWidth;
    }

    public static void setRectangleWidth(double rectangleWidth) {
        VisualMethods.rectangleWidth = rectangleWidth;
    }

    public void initialRectangles() {
        for(int i = 0; i < numRectangles; ++i) {
            Random random = new Random();
            int randomHeight = random.nextInt(350) + 40;
            SortingRectangles rect = new SortingRectangles();
            rect.setX((double)i * rectangleWidth);
            rect.setY(canvas.getHeight() - (double)randomHeight);
            rect.setWidth(rectangleWidth);
            rect.setHeight((double)randomHeight);
            Color c = this.getAColor();
            rect.setColor(c);
            rectanglesArrayList.add(rect);
            gc.setFill(c);
            gc.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        }

    }

    private Color getAColor() {
        Random random = new Random();
        float rand1 = random.nextFloat();
        float rand2 = random.nextFloat();
        float rand3 = random.nextFloat();
        float rand4 = random.nextFloat();
        if (rand4 < 0.5F) {
            rand4 = (rand4 + 0.5f);
        }

        Color color = new Color(rand1, rand2, rand3, rand4);
        return color;
    }
}
