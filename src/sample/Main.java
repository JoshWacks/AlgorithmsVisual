//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


//TODO add a feature to check the time comparison with the same data set
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    private static double width = 0.0D;
    private static double height = 0.0D;
    private static VisualMethods vm;
    private static Algorithms alg;


    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Visual Algorithm");
        primaryStage.setResizable(false);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        width = screenBounds.getWidth();
        height = screenBounds.getHeight() - 35.0D;

        Group group=new Group();

        vm = new VisualMethods(width, height,group);
        vm.initialRectangles();

        Canvas canvas= VisualMethods.getCanvas();

        alg=new Algorithms();

        group.getChildren().add(canvas);

        Scene scene = new Scene(group, width, height);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

//        alg.selectionSort();
//        VisualMethods.setSelectedSort("SelectionSort");
//
//        alg.insertionSort();
//        VisualMethods.setSelectedSort("InsertionSort");

//        alg.bubbleSort();
//        VisualMethods.setSelectedSort("BubbleSort");

        alg.callQuickSort();
        VisualMethods.setSelectedSort("QuickSort");


        vm.beginSwaps();
//



    }

    public static void main(String[] args) {
        launch(args);
    }
}
