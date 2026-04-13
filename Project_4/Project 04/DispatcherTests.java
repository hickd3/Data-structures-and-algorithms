import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class  DispatcherTests{
    public static void main(String[] args) {
    // Instantiate the dispatcher
    LeastWorkDispatcher dispatcher = new LeastWorkDispatcher(3); // Assuming a dispatcher with 3 servers

    // Read the file and process the jobs
    try (BufferedReader br = new BufferedReader(new FileReader("JobSequence_x_y.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            // Parse the line to extract arrival time and total processing time
            String[] values = line.split(",");
            double arrivalTime = Double.parseDouble(values[0]);
            double totalProcessingTime = Double.parseDouble(values[1]);

            // Create a new job with the extracted values
            Job job = new Job(arrivalTime, totalProcessingTime);

            // Dispatch the job
            Server selectedServer = dispatcher.pickServer(job);

            // Print the job and the selected server
            System.out.println("Job: " + job);
            System.out.println("Selected Server: " + selectedServer);
            System.out.println();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
