/*
 * File: JobDispatcher.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 04
 * 
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

//manages a collection of Servers and track the system time
public abstract class JobDispatcher {
    protected List<Server> serverList;
    protected double systemTime;
    //constructs a JobDispatcher with k total Servers
    public JobDispatcher(int k) {
        serverList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            serverList.add(new Server());
        }
        systemTime = 0.0;
    }
    //method updates the current time to the specified time and calls the processTo method for each Server it maintains
    public void advanceTimeTo(double time) {
        for (Server server : serverList) {
            server.processTo(time);
        }
        systemTime = time;
    }
    //advances the time to the earliest point when all jobs will have completed. 
    public void finishUp() {
        double maxFinishTime = Double.NEGATIVE_INFINITY;
        for (Server server : serverList) {
            double serverFinishTime = server.setFinishTime();
            if (serverFinishTime > maxFinishTime) {
                maxFinishTime = serverFinishTime;
            }
        }
        advanceTimeTo(maxFinishTime);
    }
    //advances the time to job's arrival time, picksand adds the Server appropriate for job
    public void handleJob(Job job) {
        double jobArrivalTime = job.getArrivalTime();
        advanceTimeTo(jobArrivalTime);
        Server server = pickServer(job);
        server.addJob(job);
    }
    //polls each Job from the specified queue of Jobs and calls handleJob on them, then after all have been handeled it calls finishUp()
    public void handleJobs(Queue<Job> jobs) {
        while (!jobs.isEmpty()) {
            Job job = jobs.poll();
            handleJob(job);
        }
        finishUp();
    }
    // this method is abstract
    public abstract Server pickServer(Job j);

public static void main(String[] args) {
    JobDispatcher dispatcher = new LeastWorkDispatcher(3);

    // Creating and adding jobs to the dispatcher
    Job job1 = new Job(0.0, 10.0);
    Job job2 = new Job(1.0, 8.0);
    Job job3 = new Job(2.0, 12.0);
    Job job4 = new Job(3.0, 6.0);
    Queue<Job> jobs = new LinkedList<>(Arrays.asList(job1, job2, job3, job4));
    dispatcher.handleJobs(jobs);

    // Processing jobs in the dispatcher
    dispatcher.advanceTimeTo(4.0);

    // Print the finish time of each job
    for (Server server : dispatcher.serverList) {
        for (Job job : server.queueOfJobs) {
            System.out.println("Job Finish Time: " + job.getFinishTime());
        }
    }
} 
}
    

    


