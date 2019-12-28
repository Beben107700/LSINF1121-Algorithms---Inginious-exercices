

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<Item> implements Iterable<Item> {
    private long nOp = 0; // count the number of operations
    private int n;          // size of the stack
    private Node  last;   // trailer of the list

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    public CircularLinkedList() {
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private long nOp() {
        return nOp;
    }



    /**
     * Append an item at the end of the list
     * @param item the item to append
     */
    public void enqueue(Item item) {
        if(last == null){
            Node niounode = new Node();
            niounode.next = niounode;
            niounode.item=item;
            last = niounode;
            n++;
            nOp++;
            return;
        }
        Node newnode = new Node();
        newnode.next = last.next;
        newnode.item = item;
        last.next = newnode;
        last = newnode;
        n++;
        nOp++;
    }
    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) {
        if(index >=n || n<0)throw new IndexOutOfBoundsException();
        if(index == 0){//remove first
            if(n == 1){//LAST NODE
                Item ret = last.item;
                last.item = null;
                last.next = null;
                last = null;
                n--;
                nOp++;
                return ret;
            }
            Node current;
            Node previous;
            previous = last;
            current = last.next;


            Item ret = current.item;
            current.item = null;

            previous.next = current.next;
            current.next = null;
            last = previous;
            n--;
            nOp++;
            return ret;
        }
        if(index == (n-1)){//remove last
            Node current;
            Node previous;
            current = last;
            previous = last;
            while(previous.next != last){
                previous = previous.next;
            }

            n--;
            nOp++;
            Item ret = current.item;
            current.item = null;
            previous.next = current.next;
            current.next = null;
            last = previous;
            return ret;
        }
        Node current;
        Node previous;
        current = last.next;
        previous = last;
        for(int i = 0; i<index; i++){
            current = current.next;
            previous = previous.next;
        }
        Item ret = current.item;
        current.item = null;
        previous.next = current.next;
        current.next = null;
        nOp++;
        n--;
        return ret;



    }


    /**
     * Returns an iterator that iterates through the items in FIFO order.
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * Implementation of an iterator that iterates through the items in FIFO order.
     *
     */
    private class ListIterator implements Iterator<Item> {
        long noperations;
        Node runner;
        boolean first = true;
        boolean emptylist;

        public ListIterator(){
            noperations = nOp;
            if(last == null){
                emptylist = true;
                runner = null;
                return;
            }
            emptylist = false;
            runner= last.next;
        }

        @Override
        public boolean hasNext() {
            if(noperations != nOp)throw new ConcurrentModificationException();
            if(emptylist)return false;
            if(runner == last.next && first == false)return false;
            //if(runner == last)return false;
            return true;
        }

        @Override
        public Item next() {
            if(noperations != nOp)throw new ConcurrentModificationException();
            if(emptylist)throw new NoSuchElementException();
            if(runner == last.next && first == false)throw new NoSuchElementException();
            first = false;
            Item ret = runner.item;
            runner = runner.next;
            return ret;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}