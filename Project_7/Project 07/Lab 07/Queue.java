import java.util.LinkedList;

public class MyQueue<T> implements Queue<T> {

    private LinkedList<T> queue;

    public MyQueue() {
        queue = new LinkedList<>();
    }

    @Override
    public boolean offer(T data) {
        return queue.offer(data);
    }

    @Override
    public T peek() {
        return queue.peek();
    }

    @Override
    public T poll() {
        return queue.poll();
    }
}

 
