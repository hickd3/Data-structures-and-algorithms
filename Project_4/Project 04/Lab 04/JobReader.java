import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class JobReader {
    
    public Queue<Job> readJobFile(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            Queue<Job> jobSequence = new LinkedList<>();

            String line = br.readLine(); // Read the first line (header) and discard it

            while (line != null) {
                String[] arr = line.split(",");
                double arrivalTime = Double.parseDouble(arr[0]);
                double processingTime = Double.parseDouble(arr[1]);
                Job newJob = new Job(arrivalTime, processingTime);
                jobSequence.offer(newJob);
                line = br.readLine();
            }

            br.close();
            return jobSequence;
        } catch (FileNotFoundException ex) {
            System.out.println("JobReader.readJobFile():: unable to open file " + filename + ": file not found");
        } catch (IOException ex) {
            System.out.println("JobReader.readJobFile():: error reading file " + filename);
        }

        return null;
    }
}
