package CpuSimulation;

import CpuSimulation.Scheduler.Task;
import GUI.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tserpes on 2/12/2015 modified by it21370 on 2/2/2016.
 */
public class FCFS extends Thread {

    private final int WhichCoreAmI;

    public FCFS(int WhichCoreAmI) {
        this.WhichCoreAmI = WhichCoreAmI;
    }

    @Override
    public void run() {
        try {
            FCFS.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(FCFS.class.getName()).log(Level.SEVERE, null, ex);
        }
        int timeElapsed = 0;
        while (true) {
            Task t = MainFrame.getNotShortedTask();
            try {
                t.execute();
            } catch (NullPointerException e) {
                try {
                    FCFS.sleep(30);
                    t = MainFrame.getNotShortedTask();
                    t.execute();
                } catch (InterruptedException ex) {
                }
            }
            timeElapsed++;
            MainFrame.updateFCFSCycles(WhichCoreAmI, timeElapsed);
            if (t.isCompleted()) {
                MainFrame.taskCompleted(t);
                t.setResponseAndWaitingTime(timeElapsed);

                if (!MainFrame.isRunning()) {
                    break;
                }
            }
        }
        this.stop();
    }
}
