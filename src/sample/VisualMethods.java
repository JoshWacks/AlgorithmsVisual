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

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class VisualMethods {
    private static int numRectangles = 20;
    private static double rectangleWidth;
    private static Canvas canvas;
    private static GraphicsContext gc;
    private static Group group;

    protected static ArrayList<SortingRectangles> rectanglesArrayList = new ArrayList();
    protected static Queue<SortingRectangles> sortingRectanglesQueue=new LinkedList<>();
    protected static String selectedSort="";

    private static Config config;

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

    public static void setSelectedSort(String selectedSort) {
        VisualMethods.selectedSort = selectedSort;
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
            text.setText(randomHeight+"");
            text.setTextAlignment(TextAlignment.JUSTIFY);
            text.setFont(new Font(18));
            text.setX(rect.getX()+15);
            text.setY(rect.getY()-10);


            group.getChildren().add(text);
            rect.setText(text);
//            gc.setFill(Color.WHITE);
//            gc.fillText(randomHeight+"",rect.getX()+(rectangleWidth/2.0),rect.getY()+(rect.getHeight()/2.0));
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

        return new Color(rand1, rand2, rand3, rand4);
    }



    private void repaint(SortingRectangles rect1,SortingRectangles rect2){//repaints the rectangles during transition as it paints over during transition

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

    private void repaintAll(){//repaints all the rectangles during transition
        for(SortingRectangles r:rectanglesArrayList){

            gc.clearRect(r.getX(),r.getY(),rectangleWidth,r.getHeight());
            gc.setFill(r.getColor());
            gc.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());//repaints that rectangle

            Text text=r.getText();
            text.setX(r.getX()+15);
            text.setY(r.getY()-10);//sets the text in their correct position as it sometimes floats off, may be because of bufferoverflow exception

        }
    }

    //TODO try and create a method to clear and redraw rectangles efficiently

    private void liftRectangles(SortingRectangles rect1,SortingRectangles rect2){//Method to lift the rectangles

        gc.clearRect(rect1.getX(),rect1.getY(),rectangleWidth,rect1.getHeight());
        gc.clearRect(rect2.getX(),rect2.getY(),rectangleWidth,rect2.getHeight());//first erases the 2 rectangles to be swapped

        rect1.getText().setY(200-10);
        gc.setFill(rect1.getColor());
        rect1.setY(200);
        gc.fillRect(rect1.getX(),200,rectangleWidth,rect1.getHeight());//redraws them higher

        rect2.getText().setY(200-10);
        if(selectedSort.equals("InsertionSort")||selectedSort.equals("SelectionSort")){//We want to highlight which rectangle is being checked
            gc.setFill(Color.rgb(0,255,0,1));
        }else {
            gc.setFill(rect2.getColor());
        }
        rect2.setY(200);
        gc.fillRect(rect2.getX(),200,rectangleWidth,rect2.getHeight());
    }

    private void horzRectangles(SortingRectangles rect1,SortingRectangles rect2,double[] tempX1,double[] tempX2){//Method to control the horizontal movement of both rectangles

        gc.setFill(rect1.getColor());
        gc.clearRect(tempX1[0],200,rectangleWidth,rect1.getHeight());//clearing
        tempX1[0] = tempX1[0] +4;//increment right
        gc.fillRect(tempX1[0],200,rectangleWidth,rect1.getHeight());//drawing
        rect1.getText().setX(tempX1[0]+15);

        if(selectedSort.equals("InsertionSort")||selectedSort.equals("SelectionSort")){//We want to highlight which rectangle is being checked on insertionSort
            gc.setFill(Color.rgb(0,255,0,1));
        }else {
            gc.setFill(rect2.getColor());
        }

        gc.clearRect(tempX2[0],200,rectangleWidth,rect2.getHeight());
        tempX2[0] = tempX2[0] -4;//increment left
        gc.fillRect(tempX2[0],200,rectangleWidth,rect2.getHeight());
        rect2.getText().setX(tempX2[0]+15);
    }

    private void dropRectangles(SortingRectangles rect1,SortingRectangles rect2,double[] tempX1,double[] tempX2,double storeX1,double storeX2){//Method to drop the rectangles
        gc.clearRect(tempX1[0],200,rectangleWidth,rect1.getHeight());
        rect1.setX(storeX2);
        rect1.setY(canvas.getHeight()-rect1.getHeight());//Places the rectangle back on the base
        rect1.getText().setY(rect1.getY()-10);
        rect1.getText().setX(rect1.getX()+15);
        gc.setFill(rect1.getColor());
        gc.fillRect(rect1.getX(),rect1.getY(),rectangleWidth,rect1.getHeight());


        gc.clearRect(tempX2[0],200,rectangleWidth,rect2.getHeight());
        rect2.setX(storeX1);
        rect2.setY(canvas.getHeight()-rect2.getHeight());//Places the rectangle back on the base
        rect2.getText().setY(rect2.getY()-10);
        rect2.getText().setX(rect2.getX()+15);
        gc.setFill(rect2.getColor());
        gc.fillRect(rect2.getX(),rect2.getY(),rectangleWidth,rect2.getHeight());

    }


    private void showSwaps(){
        //TODO add a slider to control the speed of the swaps

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        if(sortingRectanglesQueue.size()==0){//we are done sorting
            executorService.shutdown();
            config.stopTimer();
        }
        SortingRectangles rect1=sortingRectanglesQueue.remove();
        SortingRectangles rect2=sortingRectanglesQueue.remove();

        Runnable Lift=()-> liftRectangles(rect1,rect2);

        Platform.runLater(() -> {//Method of running on the UI thread
            executorService.schedule(Lift,600,TimeUnit.MILLISECONDS);

        });


        double storeX1=rect1.getX();//We need to store the original x and y
        final double[] tempX1 = {storeX1};
        double storeX2=rect2.getX();
        final double[] tempX2 = {storeX2};

        Runnable move= () -> {
            horzRectangles(rect1,rect2,tempX1,tempX2);

            if(storeX2<=tempX1[0]&&tempX2[0]<=storeX1){//Both have reached their correct place
                executorService.shutdown();

                dropRectangles(rect1,rect2,tempX1,tempX2,storeX1,storeX2);//Places the rectangles back on the base

                repaintAll();//repaints all to remove any weird paint changes
                showSwaps();//recursively does the next set of swaps

            }
            if(!selectedSort.equals("BubbleSort")) {
                repaint(rect1, rect2);//repaints all the required the rectangles as we sometimes draw over them
            }


        };

        Platform.runLater(() -> {//Method of running on the UI thread
            executorService.scheduleAtFixedRate(move, 1000, 30, TimeUnit.MILLISECONDS);//every 25 milliseconds these methods are run

        });

    }
    

    public void beginSwaps(){

        config=new Config(group);

        config.startTimer();//begins the timer here
        Platform.runLater(() -> {//Method of running on the UI thread
          config.setTimeOnScreen();

        });

        showSwaps();
    }

}
