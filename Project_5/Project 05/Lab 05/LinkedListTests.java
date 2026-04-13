import java.util.LinkedList;
public class LinkedListTests {

    public static void main(String[] args) {
        // case 1: testing LinkedList()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();

            // verify
            System.out.println(ll + " != null");

            // test
            assert ll != null : "Error in LinkedList::LinkedList()";
        }

        // case 2: testing add(T item)
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(i);
            }

            // verify
            System.out.println(ll.size() + " == 5");

            // test
            assert ll.size() == 5 : "Error in LinkedList::add(T item) or LinkedList::size()";
        }

        // case 3: testing add(int index, T item)
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            ll.add(0, 1);
            ll.add(1, 2);
            ll.add(1, 3);
            ll.add(0, 4);
            ll.add(4, 5);
            ll.add(3, 6);

            // verify
            System.out.println(ll.size() + " == 6");

            // test
            assert ll.size() == 6 : "Error in LinkedList::add(int index, T item) or LinkedList::size()";
        }

        // case 4: testing clear()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i : new int[] { 1, 2, 3 }) {
                ll.add(i);
            }
            ll.clear();

            // verify
            System.out.println(ll.size() + " == 0");

            // test
            assert ll.size() == 0 : "Error in LinkedList::clear()";
        }

        // case 5: testing contains()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 3; i++) {
                ll.add(2 * i);
            }

            // verify
            System.out.println(ll.contains(0) + " == true");
            System.out.println(ll.contains(4) + " == true");
            System.out.println(ll.contains(3) + " == false");

            // test
            assert ll.contains(0) : "Error in LinkedList::contains()";
            assert ll.contains(4) : "Error in LinkedList::contains()";
            assert !ll.contains(3) : "Error in LinkedList::contains()";
        }

        // case 6: testing equals()
        {
            // setup
            LinkedList<Integer> list1 = new LinkedList<Integer>();
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            LinkedList<Integer> list3 = new LinkedList<Integer>();
            LinkedList<Integer> list4 = new LinkedList<Integer>();
            for (int i = 0; i < 3; i++) {
                list1.add(i);
                list2.add(i);
                list3.add(i);
                list4.add(i);
            }
            list3.add(4);
            list4.add(5);

            // verify
            System.out.println(list1.equals(list2) + " == true");
            System.out.println(list2.equals(list3) + " == false");
            System.out.println(list3.equals(list4) + " == false");
          

            // test
            assert list1.equals(list2) : "Error in LinkedList::equals()";
            assert !list2.equals(list3) : "Error in LinkedList::equals()";
            assert !list3.equals(list4) : "Error in LinkedList::equals()";
           
        }

        // case 7: testing get()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(4-i);
            }

            // verify
            System.out.println(ll.get(0) + " == 0");
            System.out.println(ll.get(3) + " == 3");
            System.out.println(ll.get(4) + " == 4");

            // test
            assert ll.get(0) == 0 : "Error in LinkedList::get()";
            assert ll.get(3) == 3 : "Error in LinkedList::get()";
            assert ll.get(4) == 4 : "Error in LinkedList::get()";
        }

        // case 8: testing isEmpty()
        {
            // setup
            LinkedList<Integer> list1 = new LinkedList<Integer>();
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            list2.add(5);

            // verify
            System.out.println(list1.isEmpty() + " == true");
            System.out.println(list2.isEmpty() + " == false");

            // test
            assert list1.isEmpty() : "Error in LinkedList::isEmpty()";
            assert !list2.isEmpty() : "Error in LinkedList::isEmpty()";
        }

        // case 9: testing remove()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(4-i);
            }

            int remove0 = ll.remove();
            int remove1 = ll.remove();

            // verify
            System.out.println(remove0 + " == 0");
            System.out.println(remove1 + " == 1");

            // test
            assert remove0 == 0 : "Error in LinkedList::remove()";
            assert remove1 == 1 : "Error in LinkedList::remove()";
        }

        // case 10: testing remove(int index)
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 8; i++) {
                ll.add(7-i);
            }
            int remove0 = ll.remove(0);
            int remove3 = ll.remove(3);
            int remove5 = ll.remove(5);

            // verify
            System.out.println(remove0 + " == 0");
            System.out.println(remove3 + " == 4");
            System.out.println(remove5 + " == 7");

            // test
            assert remove0 == 0 : "Error in LinkedList::remove()";
            assert remove3 == 4 : "Error in LinkedList::remove()";
            assert remove5 == 7 : "Error in LinkedList::remove()";
        }

        // case 11: testing add(int index, T item) and iterator()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            ll.add(0, 1);
            ll.add(1, 4);
            ll.add(1, 2);
            ll.add(0, 0);
            ll.add(4, 5);
            ll.add(3, 3);

            // verify
            int counter = 0;
            for (int val : ll) {
                System.out.println(val + " == " + counter);
                counter++;
            }

            // test
            counter = 0;
            for (int val : ll) {
                assert val == counter : "Error in LinkedList::add(int index, T item) or LinkedList::iterator()";
                counter++;
            }
        }
        System.out.println("Done testing LinkedList!");
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();

            // offer elements to the queue
            ll.offer(10);
            ll.offer(20);
            ll.offer(30);

            // verify
            System.out.println(ll.peek() + " == 10");

            // test
            assert ll.peek() == 10 : "Error in LinkedList::peek()";

            // remove elements from the queue
            int remove1 = ll.poll();
            int remove2 = ll.poll();

            // verify
            System.out.println(remove1 + " == 10");
            System.out.println(remove2 + " == 20");

            // test
            assert remove1 == 10 : "Error in LinkedList::poll()";
            assert remove2 == 20 : "Error in LinkedList::poll()";
        }

        // case 13: testing Queue interface methods with empty queue
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();

            // verify
            System.out.println(ll.isEmpty() + " == true");
            System.out.println(ll.peek() + " == null");

            // test
            assert ll.isEmpty() : "Error in LinkedList::isEmpty()";
            assert ll.peek() == null : "Error in LinkedList::peek()";

            // try removing an element
            Integer remove = ll.poll();

            // verify
            System.out.println(remove + " == null");

            // test
            assert remove == null : "Error in LinkedList::poll()";
        }

        System.out.println("Done testing LinkedList!");
    }
    // case 12: testing push() and pop()
{
    // setup
    LinkedList<Integer> stack = new LinkedList<Integer>();

    // push elements onto the stack
    stack.push(10);
    stack.push(20);
    stack.push(30);

    // verify
    System.out.println(stack.peek() + " == 30");

    // test
    assert stack.peek() == 30 : "Error in LinkedList::push() or LinkedList::peek()";

    // pop elements from the stack
    int pop1 = stack.pop();
    int pop2 = stack.pop();

    // verify
    System.out.println(pop1 + " == 30");
    System.out.println(pop2 + " == 20");

    // test
    assert pop1 == 30 : "Error in LinkedList::pop()";
    assert pop2 == 20 : "Error in LinkedList::pop()";
}

// case 13: testing empty stack
{
    // setup
    LinkedList<Integer> stack = new LinkedList<Integer>();

    // verify
    System.out.println(stack.isEmpty() + " == true");

    // test
    assert stack.isEmpty() : "Error in LinkedList::isEmpty()";

    // try popping an element
    try {
        stack.pop();
    } catch (Exception e) {
        System.out.println("Exception caught: " + e.getMessage());
    }
}
    
}
