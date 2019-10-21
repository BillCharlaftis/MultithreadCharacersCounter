package CpuSimulation;

import CpuSimulation.Scheduler.Task;
import GUI.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tserpes on 2/12/2015 modified by it21370 on 2/2/2016.
 */
public class SRTF extends Thread {

    private final int WhichCoreAmI;

    public SRTF(int WhichCoreAmI) {
        this.WhichCoreAmI = WhichCoreAmI;
    }

    @Override
    public void run() {
        try {
            SRTF.sleep(30);
        } catch (InterruptedException ex) {
            Logger.getLogger(FCFS.class.getName()).log(Level.SEVERE, null, ex);
        }
        int timeElapsed = 0;
        while (true) {
            Task t = MainFrame.getShortedTask();
            t.execute();
            timeElapsed++;
            MainFrame.updateSRTFcpuCycles(WhichCoreAmI, timeElapsed);
            if (t.isCompleted()) {
                MainFrame.taskCompleted(t);
                t.setResponseAndWaitingTime(timeElapsed);
            }

            if (!MainFrame.isRunning()) {
                break;
            }
        }
        this.stop();
    }
}
