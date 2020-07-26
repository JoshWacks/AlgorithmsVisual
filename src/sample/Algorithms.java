package sample;

import java.util.ArrayList;

public class Algorithms  {
    private static VisualMethods vm;

    private static ArrayList<SortingRectangles> rectanglesArrayList = new ArrayList();
    private final int arrLength;

    public Algorithms(){
        Main m=new Main();
        vm=m.getVm();
        rectanglesArrayList=VisualMethods.getRectangles();
        arrLength=rectanglesArrayList.size();

    }

    public void bubbleSort(){


        for(int i=0;i<arrLength;i++){

            for(int j=1;j<(arrLength-i);j++){
                if(rectanglesArrayList.get(j-1).getHeight()>rectanglesArrayList.get(j).getHeight()){
                    vm.swap(rectanglesArrayList.get(j-1),rectanglesArrayList.get(j));



//                    while(!vm.swap(rectanglesArrayList.get(j-1),rectanglesArrayList.get(j))){
//                        System.out.println("Waiting");
//                    }
//                    temp=rectanglesArrayList.get(j-1);
//                    rectanglesArrayList.set(j-1,rectanglesArrayList.get(j));
//                    rectanglesArrayList.set(j,temp);

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
