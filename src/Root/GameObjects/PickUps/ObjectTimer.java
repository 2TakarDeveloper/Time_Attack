package Root.GameObjects.PickUps;

import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

import static Root.scenes.GameScene.isPaused;

public class ObjectTimer extends java.util.Timer{
    private Timer timer;
    private int period;
    private int delay;
    private int interval;
    private int time;

    public ObjectTimer(){
        this.timer = new Timer();
        this.delay = 1000;
        this.period = 1000;

        counter();
    }

    public void setTime(int time){
        this.time = time;
        this.interval = time;
    }

    private void counter(){
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(()-> {
                    if(!isPaused)
                        time = interval++;
                });
            }
        }, delay, period);
    }

    public int getTime(){
        return time;
    }
}
