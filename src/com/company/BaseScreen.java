package com.company;

import javax.swing.*;

public class BaseScreen extends JComponent implements Runnable {
    private boolean isWorking;

    public BaseScreen() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void start(){
        isWorking = true;
        new Thread(this).start();
    }

    public void stop(){
        isWorking = false;
    }

    @Override
    public void run() {
        while (isWorking){
            long SLEEP_TIME = 40;
            update(SLEEP_TIME);
            repaint();
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(long tick){

    }
}
