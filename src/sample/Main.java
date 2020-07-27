//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class Main extends Application {
    private static double width = 0.0D;
    private static double height = 0.0D;
    private static VisualMethods vm;
    private static ArrayList<SortingRectangles> rectanglesArrayList = new ArrayList();
    private static Algorithms alg;


    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Visual Algorithm");
        primaryStage.setResizable(false);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        width = screenBounds.getWidth();
        height = screenBounds.getHeight() - 35.0D;
        Group group=new Group();
        vm = new VisualMethods(width, height);
        vm.initialRectangles();
        rectanglesArrayList=vm.getRectangles();
        Canvas canvas=vm.getCanvas();

        alg=new Algorithms();

        Pane pane = new Pane(canvas);
        pane.setStyle("-fx-background-color: black;");

        group.getChildren().add(pane);
        Scene scene = new Scene(group, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
//        vm.swap(rectanglesArrayList.get(2),rectanglesArrayList.get(11));
        alg.bubbleSort();
        vm.showSwaps();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
