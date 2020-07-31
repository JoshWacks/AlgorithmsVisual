package sample;

public class Algorithms extends VisualMethods {

    private final int arrLength;
    private static String selectedSort;//variable to declare which sort has been selected

    public Algorithms(){

        arrLength=rectanglesArrayList.size();

    }

    public static String getSelectedSort() {
        return selectedSort;
    }

    public static void setSelectedSort(String selectedSort) {
        Algorithms.selectedSort = selectedSort;
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


    public void displayNums(){
        for(SortingRectangles sr:rectanglesArrayList){
            System.out.print(sr.getHeight()+" ");
        }
        System.out.println();
    }
}
