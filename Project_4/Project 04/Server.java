/*
 * File: Server.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 04
 * 
*/
import java.util.LinkedList;
import java.util.Queue;

// Server class to process, manage, track, and offer jobs of a queue
public class Server{
    Queue<Job> queueOfJobs;
    private double systemTime;

    //this constructor initializes the fields it needs for the other methods to work
    public Server(){
        systemTime = 0.0;
        queueOfJobs = new LinkedList<>();
    }
    //adds the specified Job job into the queue
    public void addJob(Job job){
        queueOfJobs.offer(job);

    }
    //processes jobs in the queue in the order of arrival and notes when a job is completed
    public void processTo(double time){
        while (!queueOfJobs.isEmpty() && systemTime < time) {
            Job job = queueOfJobs.peek();
            double jobTimeRemaining = job.getTimeRemaining();
            double timeToProcess = Math.min(time - systemTime, jobTimeRemaining);

            job.process(timeToProcess);
            systemTime += timeToProcess;

            if (job.isFinished()) {
                job.setFinishTime(systemTime);
                queueOfJobs.poll();
            }
        }
    }
    //returns the total remaining processing time in this Server's queue
    public double remainingWorkInQueue(){
        double remainingProcessingTime = 0.0;
        for (Job job : queueOfJobs) {
            remainingProcessingTime += job.getTimeRemaining();
        }
        return remainingProcessingTime; 
    
    }
    //returns the number of Jobs in this Server's queue
    public int size(){
        return queueOfJobs.size();
    }
    @Override
    public String toString() {
       return "Remaining Work in Queue: " + remainingWorkInQueue();

    }
   

    public static void main(String[] args) {
        // Create a server
        Server server = new Server();

        // Create some jobs
        Job job1 = new Job(0.0, 5.0);
        Job job2 = new Job(1.0, 3.0);
        Job job3 = new Job(2.0, 2.0);

        // Add jobs to the server
        server.addJob(job1);
        server.addJob(job2);
        server.addJob(job3);

        // Process jobs until a specified time
        double timeToProcess = 4.0;
        server.processTo(timeToProcess);

        // Print the remaining work in the server's queue
        System.out.println("Remaining work in queue: " + server.remainingWorkInQueue());

        // Print the number of jobs in the server's queue
        System.out.println("Number of jobs in queue: " + server.size());
    }
    public double setFinishTime() {
        return 0;
    }
}