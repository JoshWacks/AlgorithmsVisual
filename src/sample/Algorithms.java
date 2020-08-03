package sample;

import java.util.Random;

public class Algorithms extends VisualMethods {

    private final int arrLength;


    public Algorithms(){

        arrLength=rectanglesArrayList.size();

    }

    public void displayNums(){
        for(SortingRectangles sr:rectanglesArrayList){
            System.out.print(sr.getHeight()+" ");
        }
        System.out.println();
    }

    private void swap(int index1, int index2){//Utility function to swap two rectangles throughout the class and show these swaps later
        //TODO check why we must add to queue first

        sortingRectanglesQueue.add(rectanglesArrayList.get(index1));//adds the 2 rectangles to the queue to show the swap
        sortingRectanglesQueue.add(rectanglesArrayList.get(index2));

        SortingRectangles temp=rectanglesArrayList.get(index1);//Performs the actual swap
        rectanglesArrayList.set(index1,rectanglesArrayList.get(index2));
        rectanglesArrayList.set(index2,temp);


    }

    public void bubbleSort(){
        SortingRectangles temp;

        for(int i=0;i<arrLength;i++){

            for(int j=1;j<(arrLength-i);j++){
                if(rectanglesArrayList.get(j-1).getHeight()>rectanglesArrayList.get(j).getHeight()){
                    swap(j-1,j);
                }
            }
        }


    }

    public void insertionSort(){

        SortingRectangles check;//The rectangle we are about to check
        int j;
        for (int i=1;i<arrLength;i++){
            check=rectanglesArrayList.get(i);
            j=i-1;
            //Keep on moving 1 back until we have found the rectangles place
            while (j>=0 &&rectanglesArrayList.get(j).getHeight()>check.getHeight()){

                sortingRectanglesQueue.add(rectanglesArrayList.get(j));
                sortingRectanglesQueue.add(check);

                rectanglesArrayList.set(j+1,rectanglesArrayList.get(j));
                j=j-1;
            }
            rectanglesArrayList.set(j+1,check);//Sets the checking rectangle in its correct place
        }
    }

    public void selectionSort(){
        int posMinElement;//stores the postion of the smallest element in the unsorted array
        SortingRectangles temp;

        for(int i=0;i<arrLength-1;i++){
            posMinElement=i;
            for(int j=i+1;j<arrLength;j++){//We only want to loop through the unsorted array, that's way j=i+1
                if(rectanglesArrayList.get(j).getHeight()<rectanglesArrayList.get(posMinElement).getHeight()){
                    posMinElement=j;
                }
            }

            if(posMinElement!=i) {//We only want to swap if it is a smaller element,still works without if but more efficient and only want to show swap if there is one
//                sortingRectanglesQueue.add(rectanglesArrayList.get(i));
//                sortingRectanglesQueue.add(rectanglesArrayList.get(posMinElement));
//                temp = rectanglesArrayList.get(posMinElement);
//                rectanglesArrayList.set(posMinElement, rectanglesArrayList.get(i));
//                rectanglesArrayList.set(i, temp);

                swap(i,posMinElement);
            }

        }
    }



    private int getPivot(int low, int high){//function to return a random pivot index within the certain range inclusive
        Random random=new Random();
        return random.nextInt((high-low)+1)+low;
    }

    private int partition(int low,int high){
        swap(low,getPivot(low,high));
        int border=low+1;

        for (int i=border;i<=high;i++){
            if(rectanglesArrayList.get(i).getHeight()<rectanglesArrayList.get(low).getHeight()){
                swap(i,border++);
            }
        }
        swap(low,border-1);
        return  border-1;
    }



    private void quickSort(int low,int high){

        int partIndex;

        if(low<high+1){//the start index is less than the end we still need to sort
            partIndex=partition(low,high);//The partitioning Index
            quickSort(low,partIndex-1);
            quickSort(partIndex+1,high);//We sort the 2 sub-arrays recursively
        }

    }

    public void callQuickSort(){
        quickSort(0,arrLength-1);
    }



}
