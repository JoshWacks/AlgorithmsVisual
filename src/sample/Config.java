package sample;


import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Config {
    private static Text timer=new Text();
    private static Group group;
    private static long startTime;
    private static ScheduledExecutorService executorService;

    private ArrayList<String>storedTimes=new ArrayList<>();
    public Config(Group gr){
        group=gr;

        Text label=new Text(20,50,"Time elapsed (hh:mm:ss:ms)");
        label.setFill(Color.WHITE);
        label.setFont(new Font(22));
        group.getChildren().add(label);

        timer.setX(20);
        timer.setY(90);
        timer.setFill(Color.ORANGERED);
        timer.setFont(new Font(22));
        group.getChildren().add(timer);
    }

    public void startTimer(){
        startTime=System.currentTimeMillis();
    }

    public void stopTimer(){
        //TODO make a system of keeping track of all the different times
        executorService.shutdown();
        storedTimes.add("BubbleSort"+timer.getText());//stores the timer for comparison late
    }


    public void setTimeOnScreen(){
        executorService= Executors.newSingleThreadScheduledExecutor();
        long[]timeSoFar={System.currentTimeMillis() - startTime};

        Runnable setTime= () -> {
            timeSoFar[0]=System.currentTimeMillis() - startTime;
          //  System.out.println(timeSoFar+"ms");
            long mili= (long) (timeSoFar[0]%1000.00);
            long seconds= (long) (timeSoFar[0]/1000.0);
            long minutes= (long) (seconds/60.0);
            long hours= (long) (minutes/60.0);
            timer.setText((hours%60.0)+" : "+(minutes%60.0)+" : "+(seconds%60.0)+" : "+mili);
        };

        Platform.runLater(() -> {//Method of running on the UI thread
            executorService.scheduleAtFixedRate(
                    setTime, 0, 100, TimeUnit.MILLISECONDS);//every 100 milliseconds these it rewrites the time on screen

        });

    }


}
