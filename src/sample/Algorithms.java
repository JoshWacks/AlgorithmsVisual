package sample;

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
                sortingRectanglesQueue.add(rectanglesArrayList.get(i));
                sortingRectanglesQueue.add(rectanglesArrayList.get(posMinElement));
                temp = rectanglesArrayList.get(posMinElement);
                rectanglesArrayList.set(posMinElement, rectanglesArrayList.get(i));
                rectanglesArrayList.set(i, temp);
            }

        }
    }



}
