
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<Item> implements Iterable<Item> {
    public static void main (String [] args){
        CircularLinkedList<Integer> a= new CircularLinkedList<>();
        a.enqueue(1);
        a.enqueue(2);
        a.enqueue(3);
        Iterator<Integer> b= a.iterator();
        boolean qhasnext=b.hasNext();
        int item1=b.next();
        qhasnext=b.hasNext();
        item1=b.next();
        qhasnext=b.hasNext();
        item1=b.next();
        qhasnext=b.hasNext();
        item1=b.next();
        int dernier2=a.last.item;
        int premier2=a.last.next.item;
        int milieu2=a.last.next.next.item;
        int theintem=a.remove(0);
        dernier2=a.last.item;
        premier2=a.last.next.item;
        milieu2=a.last.next.next.item;
        theintem=a.remove(0);
        int i=0;
    }
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

    /**
     * code de Juliette pour imprimer la liste
     */
    public void printList() {
        if(this.n==0){
            System.out.println("null");
        }
        else{
            Node iter = this.last.next;
            for(int i=0;i<this.n;i++){
                System.out.println(iter.item);
                iter=iter.next;
            }
        }
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
        if (n==0){
            last=new Node();
            last.item=item;
            last.next=last;
        }
        else{
            Node premier=last.next;
            last.next=new Node();
            last.next.item=item;
            last.next.next=premier;
            last=last.next;
        }
        nOp++;
        n++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index > n-1){
            throw new IndexOutOfBoundsException();
        }
        Node iter = last;
        for(int i = 0; i < index; i++){
            iter=iter.next;//il va s'arreter pile avant le node a retirer
        }
        Item theItem = iter.next.item;
        iter.next=iter.next.next;//plus de lien vers le node a supprimer sauf le last si c'est lui!!/!\
        if (index==n-1){
            last=iter;
        }
        if (n==1){
            last=null;
        }
        nOp++;
        n--;
        return theItem;

    }

    /**
     * Returns an iterator that iterates through the items in FIFO order.
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node iter;
        int fait;
        public ListIterator(){
            if(last!=null){
                iter=last.next;
            } else{
                iter=null;
            }
            nOp=0;
            fait=0;
        }

        public boolean hasNext()throws ConcurrentModificationException{
            if(nOp>0){
                throw new ConcurrentModificationException();
            }
            /*
            if ( iter == last ){
                return false;
            }
            return true; on ne peut pas faire ceci car on compare des objets et on est pas en c!!! */
            else if(fait==n){
                return false;
            }
            else{
                return true;
            }
        }

        public Item next() throws  NoSuchElementException{
            if(nOp>2){
                throw new ConcurrentModificationException();
            }
            else if ( !hasNext() ){
                throw new  NoSuchElementException();
            }
            else{
                Item donnee = iter.item;
                iter=iter.next;
                fait++;
                return donnee;
            }
        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

    }

}