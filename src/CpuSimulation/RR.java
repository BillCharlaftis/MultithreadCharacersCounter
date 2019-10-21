package CpuSimulation;

import CpuSimulation.Scheduler.Task;
import GUI.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tserpes on 2/12/2015 modified by it21370 on 2/2/2016.
 */
public class RR extends Thread {

    private final int WhichCoreAmI;
    private final int timeWindow;

    public RR(int timeWindow, int WhichCoreAmI) {
        this.timeWindow = timeWindow;
        this.WhichCoreAmI = WhichCoreAmI;

    }

    @Override
    public void run() {
        try {
            RR.sleep(30);
        } catch (InterruptedException ex) {
            Logger.getLogger(RR.class.getName()).log(Level.SEVERE, null, ex);
        }
        int timeElapsed = 0;
        while (true) {
            Task t = MainFrame.getNotShortedTask();
            for (int i = 0; i < timeWindow; i++) {
                t.execute();
                timeElapsed++;
                MainFrame.updateRRcpuCycles(WhichCoreAmI, timeElapsed);
                if (t.isCompleted()) {
                    MainFrame.taskCompleted(t);
                    t.setResponseAndWaitingTime(timeElapsed);
                    break;
                }
                MainFrame.RRreorder(t);
            }

            if (!MainFrame.isRunning()) {
                break;
            }

        }

        this.stop();
    }
}
