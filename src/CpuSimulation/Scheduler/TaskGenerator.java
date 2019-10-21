/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CpuSimulation.Scheduler;

import GUI.MainFrame;
import java.util.Random;

/**
 *
 * @author Billy
 */
public class TaskGenerator extends Thread {

    Task t;
    int idGen;
    int limit = 0;
//     ArrayList<Task> FreshTasks = new  ArrayList<Task>();

    /**
     * ThreadGenerator constructor .
     *
     * @param idGen :Useful for resume
     */
    public TaskGenerator(int idGen, int limit) {
        this.idGen = idGen + 1;
        this.limit = limit;
    }

    @Override
    public void run() {
        int i = 0;
        while (MainFrame.isRunning() || i < limit) {
//
//         
//                t = new Task(Integer.MAX_VALUE, idGen);
//                MainFrame.reportStarvation(idGen);

            t = new Task(new Random().nextInt(100), idGen);

            idGen++;

            MainFrame.RefillTaskList(t);
            MainFrame.TotalIncrement();
            i++;
        }

        this.stop();
    }

    public int stopGEN() {
        this.stop();
        return idGen;
    }

}
