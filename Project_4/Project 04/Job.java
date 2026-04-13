/*
 * File: Server.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 04
 * 
*/

//Defines the way jobs should run
public class Job{
    private double arrivalTime;
    private double processingTime;
    private double processedTime;
    private double finishTime;
    
    //constructs the job with the specified arrival time and necessary total processing time.
    public Job(double arrivalTime, double processingTime){
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
        this.processedTime = 0.0;
        this.finishTime = 0.0;
    }
    //this returns the arrival time of this job
    public double getArrivalTime(){
        return arrivalTime;
    }
    //this returns the total necessary processing time of the job
    public double getTotalProcessingTime(){
        return processingTime;
    }
    //returns the time spent working on this job so far
    public double getTimeProcessed(){
        return processedTime;
    }
    //returns the necessary time remaining to spend working on this job
    public double getTimeRemaining(){
        return processingTime - processedTime;
    }
    //returns true if this job has been run to completion, false otherwise
    public boolean isFinished(){
        if(getTimeRemaining() <= 0){
            return true;
        }else{
            return false;
        }
    }
    //sets the time when the job completed
    public void setFinishTime(double time){
       finishTime = time;
    }
    //returns the time when the job was completed
    public double getFinishTime(){
        return finishTime;
    }
    //returns the difference in time between the arrival and finish times
    public double timeInQueue(){
        if(finishTime > 0){
            System.out.println("Job is not finished");
        }
        return arrivalTime - finishTime;
    }
    //processes job for the specified time units of time
    public void process(double time){
        processedTime += time;
        if(isFinished()){
            finishTime = arrivalTime + processingTime;
        }
    }
    @Override
    public String toString() {
        return "Arrival Time: " + arrivalTime + ", Total Processing Time: " + processingTime;
    }
    
    

    public static void main(String[] args) {
        // Create a job
        Job job = new Job(0.0, 5.0);

        // Print job details
        System.out.println("Arrival time: " + job.getArrivalTime());
        System.out.println("Total processing time: " + job.getTotalProcessingTime());
        System.out.println("Time processed: " + job.getTimeProcessed());
        System.out.println("Time remaining: " + job.getTimeRemaining());
        System.out.println("Is finished? " + job.isFinished());

        // Process the job for 2 units of time
        job.process(2.0);

        // Print updated job details
        System.out.println("Time processed: " + job.getTimeProcessed());
        System.out.println("Time remaining: " + job.getTimeRemaining());
        System.out.println("Is finished? " + job.isFinished());

        // Set the finish time
        job.setFinishTime(7.0);

        // Print job details after setting finish time
        System.out.println("Finish time: " + job.getFinishTime());
        System.out.println("Time in queue: " + job.timeInQueue());
    }
}

