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
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.*;

import java.util.ArrayList;
import java.util.*;

public class Main extends Application {
    private static double width = 0.0D;
    private static double height = 0.0D;
    private static VisualMethods vm;
    private static Algorithms alg;
    private static Group group;

    private static Button btnBubble=new Button("Bubble Sort");
    private static Button btnQuick=new Button("Quick Sort");
    private static Button btnInsert=new Button("Insertion Sort");
    private static Button btnSelect=new Button("Selection Sort");

    private static ArrayList<Button>buttonArrayList=new ArrayList<>(Arrays.asList(btnBubble,btnQuick,btnInsert,btnSelect));


    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Visual Algorithm");
        primaryStage.setResizable(false);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        width = screenBounds.getWidth();
        height = screenBounds.getHeight() - 35.0D;

        group=new Group();

        vm = new VisualMethods(width, height,group);
        vm.initialRectangles();

        Canvas canvas= VisualMethods.getCanvas();

        alg=new Algorithms();

        group.getChildren().add(canvas);
        addButtons();

        Scene scene = new Scene(group, width, height);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void addButtons(){
        for(int i=0;i<buttonArrayList.size();i++){
            Button temp=buttonArrayList.get(i);
            if(i==0){
                temp.setStyle("-fx-background-color: Chocolate;");
                temp.setLayoutX(400);
                temp.setOnMouseClicked(runThis -> {
                    if(VisualMethods.selectedSort.equals("")) {
                        alg.bubbleSort();
                        VisualMethods.setSelectedSort("BubbleSort");
                        vm.beginSwaps();
                    }else{
                        JOptionPane.showMessageDialog(null,"There is already a sort selected");
                    }

                });
            }
            else if(i==1){
                temp.setStyle("-fx-background-color: DarkMagenta;");
                temp.setLayoutX(600);
                temp.setOnMouseClicked(runThis -> {
                    if(VisualMethods.selectedSort.equals("")) {
                        alg.callQuickSort();
                        VisualMethods.setSelectedSort("QuickSort");
                        vm.beginSwaps();
                    }else{
                        JOptionPane.showMessageDialog(null,"There is already a sort selected");
                    }

                });
            }
            else if(i==2){
                temp.setStyle("-fx-background-color: DodgerBlue;");
                temp.setLayoutX(800);
                temp.setOnMouseClicked(runThis -> {
                    if(VisualMethods.selectedSort.equals("")) {
                        alg.insertionSort();
                        VisualMethods.setSelectedSort("InsertionSort");
                        vm.beginSwaps();
                    }else{
                        JOptionPane.showMessageDialog(null,"There is already a sort selected");
                    }

                });
            }
            else{
                temp.setStyle("-fx-background-color: FireBrick;");
                temp.setLayoutX(1000);
                temp.setOnMouseClicked(runThis -> {
                    if(VisualMethods.selectedSort.equals("")) {
                        alg.selectionSort();
                        VisualMethods.setSelectedSort("SelectionSort");
                        vm.beginSwaps();
                    }else{
                        JOptionPane.showMessageDialog(null,"There is already a sort selected");
                    }

                    BorderWidths borderWidths=new BorderWidths(3);

                    temp.setBorder(new Border(new BorderStroke(Color.WHITE,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, borderWidths)));

                });
            }


            temp.setFont(new Font(22));
            temp.setTextFill(Color.BLACK);

            temp.setLayoutY(30);
            temp.setPrefSize(180,40);

            group.getChildren().add(temp);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
