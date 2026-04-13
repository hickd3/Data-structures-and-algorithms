//it picks and returns the first Server, then the next time the second, etc.
public class RoundRobinDispatcher extends JobDispatcher {
    private int currentServerIndex;

    public RoundRobinDispatcher(int k) {
        super(k);
        currentServerIndex = 0;
    }

    @Override
    public Server pickServer(Job j) {
        Server server = serverList.get(currentServerIndex);
        currentServerIndex = (currentServerIndex + 1) % serverList.size();
        return server;
    }
}