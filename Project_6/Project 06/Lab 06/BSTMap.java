/*
 * File: BSTMap.java
 * Author: Dean Hickman
 * Date: May 2023
 * Project 06
 * CS 321 A
 */

 import java.util.ArrayList;
 import java.util.Comparator;
 
 public class BSTMap<K, V> implements MapSet<K, V> {
    private static class Node<K, V> extends KeyValuePair<K, V> {
        private Node<K, V> left;
        private Node<K, V> right;
 
        public Node(K key, V value) {
            super(key, value);
            this.left = null;
            this.right = null;
        }
    }
 
     private Node<K, V> root;
     private int size;
     private Comparator<K> comparator;
 
     public BSTMap(Comparator<K> comparator) {
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            this.comparator = (o1, o2) -> ((Comparable<K>) o1).compareTo(o2);
        }
        this.root = null;
        this.size = 0;
    }
 
    public BSTMap() {
       this(null);
    }
 
    public int size() {
        return size;
    }
 
    public void clear() {
        root = null;
        size = 0;
    }
 
    public V put(K key, V value) {
        if (value == null) {
            return null;
        }
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return null;
        } else {
            return put(key, value, root);
        }
    }
 
    private V put(K key, V value, Node<K, V> cur) {
        if (comparator.compare(key, cur.getKey()) < 0) {
            if (cur.left != null) {
                return put(key, value, cur.left);
            } else {
                cur.left = new Node<>(key, value);
                size++;
                return null;
            }
        } else if (comparator.compare(key, cur.getKey()) > 0) {
            if (cur.right != null) {
                return put(key, value, cur.right);
            } else {
                cur.right = new Node<>(key, value);
                size++;
                return null;
            }
        } else {
            V oldValue = cur.getValue();
            cur.setValue(value);
            return oldValue;
        }
    }
 
    public V get(K key) {
        return get(key, root);
    }
 
    private V get(K key, Node<K, V> cur) {
        if (cur == null) {
            return null;
        }
 
        int cmp = comparator.compare(key, cur.getKey());
        if (cmp < 0) {
            return get(key, cur.left);
        } else if (cmp > 0) {
            return get(key, cur.right);
        } else {
            return cur.getValue();
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        keySet(root, keys);
        return keys;
    }

    private void keySet(Node<K, V> cur, ArrayList<K> output) {
        if (cur == null) {
            return;
        }
        keySet(cur.left, output);
        output.add(cur.getKey());
        keySet(cur.right, output);
    }

    public ArrayList<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for (K key : keySet()) {
            values.add(get(key));
        }
        return values;
    }

    public ArrayList<KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> entries = new ArrayList<>();
        for (K key : keySet()) {
            entries.add(new KeyValuePair<>(key, get(key)));
        }
        return entries;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, 0);
        return sb.toString();
    }
    private void toString(Node<K, V> node, StringBuilder sb, int level) {
        if (node == null) {
            return;
        }
         for (int i = 0; i < level; i++) {
            sb.append("  ");
        } 
        toString(node.right, sb, level + 1);
        sb.append(node.getKey()).append(": ").append(node.getValue()).append("\n");
        toString(node.left, sb, level + 1);
    }
    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(Node<K, V> cur) {
        if (cur == null) {
            return 0;
        }
        int leftDepth = maxDepth(cur.left);
        int rightDepth = maxDepth(cur.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public V remove(K key) {
        Node<K, V> toRemoveParent = null;
        Node<K, V> toRemove = findNode(key, root, toRemoveParent);

        if (toRemove == null) {
            return null;
        }
        V oldValue = toRemove.getValue();
        handleReplacement(toRemove, toRemoveParent);
        size--;
        return oldValue;
    }

    private Node<K, V> findNode(K key, Node<K, V> cur, Node<K, V> parent) {
        if (cur == null) {
            return null;
        }
        int cmp = comparator.compare(key, cur.getKey());
        if (cmp < 0) {
            return findNode(key, cur.left, cur);
        } else if (cmp > 0) {
            return findNode(key, cur.right, cur);
        } else {
            return cur;
        }
    }

    private void handleReplacement(Node<K, V> toDelete, Node<K, V> toDeleteParent) {
        if (toDelete.left == null && toDelete.right == null) {
            if (toDeleteParent == null) {
                root = null;
            } else if (toDeleteParent.left == toDelete) {
                toDeleteParent.left = null;
            } else {
                toDeleteParent.right = null;
            }
        } else if (toDelete.left == null) {
            if (toDeleteParent == null) {
                root = toDelete.right;
            } else if (toDeleteParent.left == toDelete) {
                toDeleteParent.left = toDelete.right;
            } else {
                toDeleteParent.right = toDelete.right;
            }
        } else if (toDelete.right == null) {
            if (toDeleteParent == null) {
                root = toDelete.left;
            } else if (toDeleteParent.left == toDelete) {
                toDeleteParent.left = toDelete.left;
            } else {
                toDeleteParent.right = toDelete.left;
            }
        } else {
            Node<K, V> replacement = findNextLargestNode(toDelete.right);
            handleReplacement(replacement, toDelete);
            replacement.left = toDelete.left;
            replacement.right = toDelete.right;

            if (toDeleteParent == null) {
                root = replacement;
            } else if (toDeleteParent.left == toDelete) {
                toDeleteParent.left = replacement;
            } else {
                toDeleteParent.right = replacement;
            }
        }
    }

    private Node<K, V> findNextLargestNode(Node<K, V> cur) {
        if (cur.left == null) {
            return cur;
        }
        return findNextLargestNode(cur.left);
    }
    public static void main(String[] args) {
    // this will sort the strings lexicographically (dictionary-order)
    BSTMap<String, Integer> words = new BSTMap<>();
    words.put("ten", 10);
    words.put("five", 5);
    words.put("three", 3);
    System.out.println(words);

    // this will sort the strings in reverse lexicographic order
    BSTMap<String, Integer> wordsReverse = new BSTMap<>(new Comparator<String>() {

        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }

    });
    wordsReverse.put("ten", 10);
    wordsReverse.put("five", 5);
    wordsReverse.put("three", 3);
    System.out.println(wordsReverse);
}
}



            
 
 