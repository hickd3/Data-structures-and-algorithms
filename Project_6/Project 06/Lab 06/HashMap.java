/*
 * File: HashMap.java
 * Author: Dean Hickman
 * Date: May 2023
 * Project 06
 * CS 321 A
 */

 import java.util.ArrayList;
 public class HashMap<K, V> implements MapSet<K, V> {

    private static class Node<K, V> extends KeyValuePair<K, V> {
        private Node<K, V> next;
     
 
        public Node(K key, V value) {
            super(key, value);
            this.next = null;
        }

        public void setKey(K key) {
            this.key = key;
        }
      
    }

    private int size;
    private Node<K, V>[] nodes;
    private double maxLoadFactor;
   
    public HashMap() {
        this(16, 0.75);
    }

    public HashMap(int capacity) {
        this(capacity, 0.75);
    }

    public HashMap(int capacity, double loadFactor) {
        if (capacity <= 0)
           System.out.println("Invalid capacity: " + capacity);
        if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor))
            System.out.println("Invalid load factor: " + loadFactor);

        this.size = 0;
        this.nodes = (Node<K, V>[]) new Node[capacity];
        this.maxLoadFactor = loadFactor;
    }

    private int capacity(){
        return nodes.length;
    }
    private int hash(K key){
        return Math.abs(key.hashCode() % capacity());
    }

   
    public int size(){
        return size;
    }
    public void clear(){
        size = 0; 
        nodes = (Node<K, V>[]) new Node[16];
    }
    public V put(K key, V value) {
        if (value == null)
            return null;

        int index = hash(key);
        Node<K, V> node = nodes[index];

        while (node != null) {
            if (node.getKey().equals(key)) {
                V oldValue = node.getValue();
                node.setValue(value);
                return oldValue;
            }
            node = node.next;
        }

        node = new Node<K, V>(key, value);
        node.setKey(key);
        node.setValue(value);
        node.next = nodes[index];
        nodes[index] = node;
        size++;

        if (size > maxLoadFactor * capacity())
            resizeTable();

        return null;
    }

    private void resizeTable() {
        int newCapacity = capacity() * 2;
        Node<K, V>[] newNodes = (Node<K, V>[]) new Node[newCapacity];

        for (Node<K, V> node : nodes) {
            while (node != null) {
                int newIndex = hash(node.getKey());
                Node<K, V> next = node.next;
                node.next = newNodes[newIndex];
                newNodes[newIndex] = node;
                node = next;
            }
        }

        nodes = newNodes;
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = nodes[index];

        while (node != null) {
            if (node.getKey().equals(key))
                return node.getValue();
            node = node.next;
        }

        return null;
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        Node<K, V> node = nodes[index];

        while (node != null) {
            if (node.getKey().equals(key))
                return true;
            node = node.next;
        }

        return false;
    }

    public V remove(K key) {
        int index = hash(key);
        Node<K, V> node = nodes[index];
        Node<K, V> prev = null;

        while (node != null) {
            if (node.getKey().equals(key)) {
                V value = node.getValue();

                if (prev != null)
                    prev.next = node.next;
                else
                    nodes[index] = node.next;

                size--;
                return value;
            }

            prev = node;
            node = node.next;
        }

        return null;
    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();

        for (Node<K, V> node : nodes) {
            while (node != null) {
                keys.add(node.getKey());
                node = node.next;
            }
        }

        return keys;
    }

    public ArrayList<V> values() {
        ArrayList<V> values = new ArrayList<>();

        for (Node<K, V> node : nodes) {
            while (node != null) {
                values.add(node.getValue());
                node = node.next;
            }
        }

        return values;
    }

    public ArrayList<KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> entries = new ArrayList<>();

        for (Node<K, V> node : nodes) {
            while (node != null) {
                entries.add(new KeyValuePair<>(node.getKey(), node.getValue()));
                node = node.next;
            }
        }

        return entries;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean isFirst = true;
        for (Node<K, V> node : nodes) {
            while (node != null) {
                if (!isFirst) {
                    sb.append(", ");
                } else {
                    isFirst = false;
                }

                sb.append(node.getKey().toString());
                sb.append("=");
                sb.append(node.getValue().toString());

                node = node.next;
            }
        }

        sb.append("}");
        return sb.toString();
    }

    public int maxDepth() {
        int maxDepth = 0;

        for (Node<K, V> node : nodes) {
            int bucketSize = 0;
            while (node != null) {
                bucketSize++;
                node = node.next;
            }
            maxDepth = Math.max(maxDepth, bucketSize);
        }

        return maxDepth;
    }
    public static void main(String[] args) {
        //Create a new HashMap
        HashMap<Integer, String> hashMap = new HashMap<>();

        //Test put() and get()
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");

        System.out.println(hashMap.get(1));  // Expected output: "One"
        System.out.println(hashMap.get(2));  // Expected output: "Two"
        System.out.println(hashMap.get(3));  // Expected output: "Three"

        // Test containsKey()
        System.out.println(hashMap.containsKey(1));  // Expected output: true
        System.out.println(hashMap.containsKey(4));  // Expected output: false

        // Test size()
        System.out.println(hashMap.size());  // Expected output: 3

        // Test keySet()
        ArrayList<Integer> keys = hashMap.keySet();
        System.out.println(keys);  // Expected output: [1, 2, 3]

        // Test values()
        ArrayList<String> values = hashMap.values();
        System.out.println(values);  // Expected output: ["One", "Two", "Three"]

        //Test entrySet()
        ArrayList<KeyValuePair<Integer, String>> entries = hashMap.entrySet();
        for (KeyValuePair<Integer, String> entry : entries) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        // Expected output:
        // 1 => "One"
        // 2 => "Two"
        // 3 => "Three"

        //Test remove()
        String removedValue = hashMap.remove(2);
        System.out.println(removedValue);  // Expected output: "Two"
        System.out.println(hashMap.containsKey(2));  // Expected output: false

        //Test clear()
        hashMap.clear();
        System.out.println(hashMap.size());  // Expected output: 0
        System.out.println(hashMap.containsKey(1));  // Expected output: false
    }
}

