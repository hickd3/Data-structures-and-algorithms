import java.util.Random;


//extend the JobDispatcher class and return a random Server
public class RandomDispatcher extends JobDispatcher {
    private Random random;

    public RandomDispatcher(int k) {
        super(k);
        random = new Random();
    }

    @Override
    public Server pickServer(Job j) {
        int randomIndex = random.nextInt(serverList.size());
        return serverList.get(randomIndex);
    }
}
