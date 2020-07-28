//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class VisualMethods {
    private static int numRectangles = 20;
    private static double rectangleWidth;
    private static Canvas canvas;
    private static GraphicsContext gc;
    private static Group group;

    protected static ArrayList<SortingRectangles> rectanglesArrayList = new ArrayList();
    protected static Queue<SortingRectangles> sortingRectanglesQueue=new LinkedList<>();


    public VisualMethods(double width, double height,Group gr) {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        rectangleWidth = width / (double)numRectangles;
        gc.setLineWidth(1.0D);
        group=gr;
    }

    public VisualMethods(){

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
            int randomHeight = random.nextInt(400) + 50;
            SortingRectangles rect = new SortingRectangles();

            rect.setX((double)i * rectangleWidth);
            rect.setY(canvas.getHeight() - (double)randomHeight);
            rect.setWidth(rectangleWidth);
            rect.setHeight(randomHeight);

            Color c = this.getAColor();
            rect.setColor(c);
            Text text=new Text();
            text.setFill(Color.WHITESMOKE);
            text.setStyle("-fx-background-color: WHITE;");
            text.setText(randomHeight+"");
            rect.setText(text);

            text.setX(rect.getX()+(rectangleWidth/2.0));
            text.setY(rect.getY()+(rect.getHeight()/2.0));

            group.getChildren().add(text);
            rect.setText(text);
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



    private void repaint(SortingRectangles rect1,SortingRectangles rect2){//repaints the rectangles during transition

        for(SortingRectangles r:rectanglesArrayList){
            if(!(r.equals(rect1)||r.equals(rect2))){//takes in the two rectangles we should never repaint
                if(r.getX()>rect1.getX()&&r.getX()<rect2.getX()) {//only between the two being redrawn
                    gc.clearRect(r.getX(),r.getY(),rectangleWidth,r.getHeight());
                    gc.setFill(r.getColor());
                    gc.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());

                }
            }
        }
    }

    public void showSwaps(){
        SortingRectangles rect1=sortingRectanglesQueue.remove();
        SortingRectangles rect2=sortingRectanglesQueue.remove();

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


        gc.clearRect(rect1.getX(),rect1.getY(),rectangleWidth,rect1.getHeight());
        gc.clearRect(rect2.getX(),rect2.getY(),rectangleWidth,rect2.getHeight());//first erases the 2 rectangles to be swapped

        gc.setFill(rect1.getColor());
        rect1.setY(200);
        gc.fillRect(rect1.getX(),200,rectangleWidth,rect1.getHeight());//redraws them higher

        gc.setFill(rect2.getColor());
        rect2.setY(200);
        gc.fillRect(rect2.getX(),200,rectangleWidth,rect2.getHeight());

        double storeX1=rect1.getX();//We need to store the original x
        final double[] tempX1 = {storeX1};
        double storeX2=rect2.getX();
        final double[] tempX2 = {storeX2};

        Runnable move= () -> {

            gc.setFill(rect1.getColor());//We are only moving the one rectangle right here so we only need that colour
            gc.clearRect(tempX1[0],200,rectangleWidth,rect1.getHeight());//clearing
            tempX1[0] = tempX1[0] +1;//increment
            gc.fillRect(tempX1[0],200,rectangleWidth,rect1.getHeight());//drawing

            gc.setFill(rect2.getColor());//We are only moving the one rectangle left here so we only need that colour
            gc.clearRect(tempX2[0],200,rectangleWidth,rect2.getHeight());
            tempX2[0] = tempX2[0] -1;
            gc.fillRect(tempX2[0],200,rectangleWidth,rect2.getHeight());

            if(storeX2<=tempX1[0]){
                executorService.shutdown();
                gc.clearRect(tempX1[0],200,rectangleWidth,rect1.getHeight());
                rect1.setX(storeX2);
                rect1.setY(canvas.getHeight()-rect1.getHeight());//Places the rectangle back on the base
                gc.setFill(rect1.getColor());
                gc.fillRect(rect1.getX(),rect1.getY(),rectangleWidth,rect1.getHeight());

                gc.clearRect(tempX2[0],200,rectangleWidth,rect2.getHeight());
                rect2.setX(storeX1);
                rect2.setY(canvas.getHeight()-rect2.getHeight());//Places the rectangle back on the base
                gc.setFill(rect2.getColor());
                gc.fillRect(rect2.getX(),rect2.getY(),rectangleWidth,rect2.getHeight());
                showSwaps();


            }
            repaint(rect1,rect2);//repaints all the required the rectangles as we sometimes draw over them


        };

        Platform.runLater(() -> {//Method of running on the UI thread
            executorService.scheduleAtFixedRate(move, 800, 10, TimeUnit.MILLISECONDS);//every 30 milliseconds these methods are run

        });
        int pos1=rectanglesArrayList.indexOf(rect1);
        int pos2=rectanglesArrayList.indexOf(rect2);
        SortingRectangles temp=rectanglesArrayList.get(pos1);
        rectanglesArrayList.set(pos1,rect2);
        rectanglesArrayList.set(pos2,temp);//Performs the actual swap in the array


    }

}
