import java.util.Iterator;    // defines the Iterator interface
import java.util.ArrayList;   
import java.util.Collections; // contains a shuffle function
import java.util.Queue; 
import java.util.Stack;

public class LinkedList<T> implements Stack<T>, Queue<T>, Iterable<T> {
    private Node<T> head;
    private int size;

    private static class Node<T>{
        private Node<T> next;
        private T data;

        public Node(T data){
            this.next = null;
            this.data = data;
        }
        public T getData(){
          return data;  
        }
        public Node getNext(){
            return next;
        }

    }

    private class LLIterator implements Iterator<T>{
        private Node<T> nodeNode;

        public LLIterator(Node<T> head){
            this.nodeNode = head;
        }

        public boolean hasNext(){
            return nodeNode != null;
        }

        public T next(){
            if (!hasNext()){
                System.out.println("There are no more elements in the list");
            }
            T data = nodeNode.getData();
            nodeNode = nodeNode.getNext();
            return data;
        }

        public void remove(){
            System.out.println("This operation does nothing");
        }
    }
    @Override
    public boolean offer(T item) {
        add(item);
        return true;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        return remove();
    }

    public Iterator<T> interator(){
        return new LLIterator(head);
    }

    public ArrayList<T> toArrayList(){
        ArrayList<T> arrayList = new ArrayList<>();
        Node<T> nodeNode = head;
        while(nodeNode != null){
            arrayList.add(nodeNode.getData());
            nodeNode = nodeNode.getNext();
        }
        return arrayList;
    }


    public LinkedList(){
        head = null;
        size = 0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        if ( size == 0){
            return true;
        } else{
            return false;
        }
    }
    public void clear(){
        size = 0; 
        head = null;
    }
    public boolean add (T data){
        Node<T> node = new Node<>(data);
        node.next = head;
        head = node;
        size++; 
    }
    public T remove(){
        if(size == 0){
            System.out.println("The list is empty");
        }
        T removeNode = head.data;
        head = head.next;
        size--;
        return removeNode;
    }
        
    public T get(int index){
        if (index < 0 || index >= size){
            System.out.println("Choose a new index, can only be between 0 and size-1");
        }
        Node<T> node = head;
        for (int i =0; i < index; i++){
            node = node.next;
        }
        return node.data;
    }
    public void add(int index, T data){
        if (index < 0 || index > size){
            System.out.println("Choose a new index, can only be between 0 and size");
        }

        if( index == 0){
            add(data);
            return;
        }
        Node<T> newNode= new Node<>(data);
        Node<T> node = head;
        for (int i =0; i < index - 1; i++){
            node = node.next; 
        }
        newNode.next= node.next;
        node.next = newNode; 
        size++;
    }
    public T remove(int index){
        if (index < 0 || index >= size){
            System.out.println("Choose a new index, can only be between 0 and size-1");
        }
        if (index == 0){
            return remove();
        }
        Node<T> prev = null;
        Node<T> node = head;
        for(int i =  0; i < index; i++){
            prev = node;
            node = node.next;
        }
        T removeData = node.data;
        prev.next = node.next;
        size--;
        return removeData;
    }
    public boolean contains(Object o){
        Node<T> node = head;
        while(node != null){
            if (node.data.equals(o)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof LinkedList)){
            return false;
        }LinkedList<?> otherList = (LinkedList<?>) o;
        if (size() != otherList.size()) {
            return false;
        }
        Node<T> nodeNode = head;
        Node<?> otherNode = otherList.head;
        while(nodeNode != null){
            if (!nodeNode.data.equals(otherNode.data)){
                return false;
            }
            nodeNode = nodeNode.next;
            otherNode= otherNode.next;
        }
        return true;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
    
    //main method
    public static void main(String[] args){
        LinkedList<String> list = new LinkedList<>();
        list.add("Dean");
        list.add("Edward");
        list.add("Hickman");

        System.out.println("List: " + list);

        // Accessing elements in the list
        System.out.println("Element at index 2: " + list.get(2));

        // Checking if an element is present in the list
        System.out.println("List contains 'Dean': " + list.contains("Dean"));
        System.out.println("List contains 'Eric': " + list.contains("Eric"));

        // Removing elements from the list
        System.out.println("Removed element: " + list.remove());
        System.out.println("List after removal: " + list);

        // Adding an element at a specific index
        list.add(1, "CA");
        System.out.println("List after adding 'CA' at index 1: " + list);

        // Removing an element from a specific index
        System.out.println("Removed element at index 2: " + list.remove(2));
        System.out.println("List after removal at index 2: " + list);

        // Clearing the list
        list.clear();
        System.out.println("List after clearing: " + list);

        // Checking if the list is empty
        System.out.println("Is the list empty? " + list.isEmpty());
    }
}



