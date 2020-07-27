package sample;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Algorithms extends VisualMethods {

    private final int arrLength;

    public Algorithms(){

        arrLength=rectanglesArrayList.size();

    }

    public void bubbleSort(){
        SortingRectangles temp;

        for(int i=0;i<arrLength;i++){

            for(int j=1;j<(arrLength-i);j++){
                if(rectanglesArrayList.get(j-1).getHeight()>rectanglesArrayList.get(j).getHeight()){
                    sortingRectanglesQueue.add(rectanglesArrayList.get(j-1));
                    sortingRectanglesQueue.add(rectanglesArrayList.get(j));

                    temp=rectanglesArrayList.get(j-1);
                    rectanglesArrayList.set(j-1,rectanglesArrayList.get(j));
                    rectanglesArrayList.set(j,temp);

                }
            }
        }


    }


    public void displayNums(){
        for(SortingRectangles sr:rectanglesArrayList){
            System.out.print(sr.getHeight()+" ");
        }
        System.out.println();
    }
}
