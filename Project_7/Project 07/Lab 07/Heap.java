
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<T> implements PriorityQueue<T> {
    private static class Node<T> {
        T data;

        public Node(T data) {
            this.data = data;
        }
    }

    private List<Node<T>> heap;
    private Comparator<T> comparator;

    public Heap() {
        this(null, false);
    }

    public Heap(boolean maxHeap) {
        this(null, maxHeap);
    }

    public Heap(Comparator<T> comparator) {
        this(comparator, false);
    }

    public Heap(Comparator<T> comparator, boolean maxHeap) {
        this.comparator = comparator != null ? comparator : (Comparator<T>) Comparator.naturalOrder();
        if (maxHeap) {
            this.comparator = this.comparator.reversed();
        }
        heap = new ArrayList<>();
    }

    @Override
    public void offer(T item) {
        Node<T> newNode = new Node<>(item);
        heap.add(newNode);
        bubbleUp(heap.size() - 1);
    }

    private void bubbleUp(int index) {
        if (index == 0) {
            return;
        }
        int parentIndex = (index - 1) / 2;
        if (comparator.compare(heap.get(index).data, heap.get(parentIndex).data) > 0) {
            swap(index, parentIndex);
            bubbleUp(parentIndex);
        }
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public T peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0).data;
    }

    @Override
    public T poll() {
        if (heap.isEmpty()) {
            return null;
        }
        T rootData = heap.get(0).data;
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        bubbleDown(0);
        return rootData;
    }

    private void bubbleDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largestIndex = index;

        if (leftChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex).data, heap.get(largestIndex).data) > 0) {
            largestIndex = leftChildIndex;
        }

        if (rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex).data, heap.get(largestIndex).data) > 0) {
            largestIndex = rightChildIndex;
        }

        if (largestIndex != index) {
            swap(index, largestIndex);
            bubbleDown(largestIndex);
        }
    }

    @Override
    public void updatePriority(T item) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).data.equals(item)) {
                heap.get(i).data = item;
                bubbleUp(i);
                bubbleDown(i);
                return;
            }
        }
    }

    private void swap(int index1, int index2) {
        Node<T> temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
 