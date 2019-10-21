/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CpuSimulation.Scheduler;

import java.util.Comparator;

/**
 *
 * @author tserpes
 */
public class Task {

    private final int workload;
    private int remainingWorkload;
    private final int id;
    private final long creationTime;   //the time that the task was first inserted in the Ready Queue
    private long lastUsedTime;
    private long waitingTime;
    private long responseTime;

    public Task(int workload, int id) {
        this.workload = workload;
        this.remainingWorkload = workload;
        this.id = id;
        this.creationTime = 0;
        this.lastUsedTime = System.currentTimeMillis();
        this.waitingTime = 0;
        this.responseTime = 0;
    }

    public void execute() {
        this.lastUsedTime = System.currentTimeMillis();
        this.remainingWorkload--;
    }

    public synchronized long getLastUsedTime() {
        return lastUsedTime;
    }

    public int getWorkload() {
        return this.workload;
    }

    public void setRemainingWorkload(int remainingWorkload) {
        this.remainingWorkload = remainingWorkload;
    }

    public int getRemainingWorkload() {
        return this.remainingWorkload;
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    public void setResponseAndWaitingTime(int executionFinishTime) {
        this.responseTime = executionFinishTime - this.creationTime;
        this.waitingTime = executionFinishTime - this.creationTime - this.workload;
    }

    public long getResponseTime() {
        return this.responseTime;
    }

    public long getWaitingTime() {
        return this.waitingTime;
    }

    public boolean isCompleted() {
        return this.remainingWorkload == 0;
    }

    public int getId() {
        return this.id;
    }

    public synchronized static Comparator orderByRemainingWorkload() {
        return new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.remainingWorkload - o2.remainingWorkload;
            }
        };
    }

    @Override
    public String toString() {
        return "id=" + id + ",workload=" + workload + ", remainingWorkload=" + remainingWorkload + ", creationTime=" + creationTime + ", waitingTime=" + waitingTime + ", responseTime=" + responseTime;
    }
}
