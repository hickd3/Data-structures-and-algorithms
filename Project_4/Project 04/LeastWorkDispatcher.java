
//picks the Server with the least remaining processing time in its queue
public class LeastWorkDispatcher extends JobDispatcher{
    public LeastWorkDispatcher(int k) {
        super(k);
    }

    @Override
    public Server pickServer(Job j) {
        Server leastWorkServer = serverList.get(0);
        double leastWork = leastWorkServer.remainingWorkInQueue();

        for (Server server : serverList) {
            double work = server.remainingWorkInQueue();
            if (work < leastWork) {
                leastWork = work;
                leastWorkServer = server;
            }
        }

        return leastWorkServer;
    }
}

