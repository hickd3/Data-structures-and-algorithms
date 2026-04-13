// picks whichever Server has the smallest-sized queue 
public class ShortestQueueDispatcher extends JobDispatcher {
    public ShortestQueueDispatcher(int k) {
        super(k);
    }

    @Override
    public Server pickServer(Job j) {
        Server shortestQueueServer = serverList.get(0);
        int shortestQueueSize = shortestQueueServer.size();

        for (Server server : serverList) {
            int queueSize = server.size();
            if (queueSize < shortestQueueSize) {
                shortestQueueSize = queueSize;
                shortestQueueServer = server;
            }
        }

        return shortestQueueServer;
    }
}