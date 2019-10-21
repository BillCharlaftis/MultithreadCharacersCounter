package Checker;

import CpuSimulation.Scheduler.Task;
import GUI.MainFrame;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 *
 * @author Billy
 */
public class detector extends TimerTask {

    ArrayList<Task> Before;
    ArrayList<Task> Completled;
    long averageWaiting;
    long averageResponse;
    int Starvations;
    long start;
    long stop;

    @Override
    public void run() {

        start = MainFrame.getStart();
        stop = System.currentTimeMillis();
        Task[] toBeChecked = MainFrame.BQ2Array();
        Before = MainFrame.getBefore();
        Completled = MainFrame.getCompleteTaskList();
        Starvations = 0;
        averageWaiting = 0;
        averageResponse = 0;
        
        if (!Before.isEmpty()&&!Completled.isEmpty()) {
            for (int i = 0; i < toBeChecked.length; i++) {
                if (toBeChecked[i].getLastUsedTime() <= stop && toBeChecked[i].getLastUsedTime() >= start) {
                    if (Before.contains(toBeChecked[i])) {
                        Starvations++;
                    }
                }
            }

            int noOfComp = 0;
            for (int j = 0; j < Completled.size(); j++) {
                if (Completled.get(j).getLastUsedTime() <= stop && Completled.get(j).getLastUsedTime() >= start) {
                    averageWaiting += Completled.get(j).getWaitingTime();
                    averageResponse += Completled.get(j).getResponseTime();
                    noOfComp++;
                }
            }

            MainFrame.add2LogTextArea("Starvations " + Starvations + ", completle  " + noOfComp + ", averageWaiting " + averageWaiting + ", averageResponse " + averageResponse);

            MainFrame.StarvationsUpdate(Starvations);
            MainFrame.updateTimes(averageWaiting/noOfComp, averageResponse/noOfComp);
            MainFrame.complBars(noOfComp * 100 / Completled.size());
        }
        MainFrame.setStart(stop);
        MainFrame.setBefore(toBeChecked, start, stop);
    }
}
