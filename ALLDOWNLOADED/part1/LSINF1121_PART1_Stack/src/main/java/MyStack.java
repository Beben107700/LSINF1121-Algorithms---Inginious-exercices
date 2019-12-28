import java.util.EmptyStackException;

public class MyStack<E> implements Stack<E> {

    private Node<E> top;        // the node on the top of the stack
    private int size;        // size of the stack

    // helper linked list class
    private class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public MyStack() {
        top=new Node(null,null);
        size=0;
    }

    /**
     * Tests if this stack is empty
     */
    @Override
    public boolean empty() {
        if(size==0){
            return true;
        }
        return false;
    }

    /**
     * Looks at the object at the top of this stack
     * without removing it from the stack
     */
    @Override
    public E peek() throws EmptyStackException {
        if(size < 1){
            throw new EmptyStackException();
        }
        return top.item;
    }

    /**
     * Removes the object at the top of this stack
     * and returns that object as the value of this function
     */
    @Override
    public E pop() throws EmptyStackException {
        if(size < 1){
            throw new EmptyStackException();
        }
        E a=top.item;
        top.item=null;
        top=top.next;
        size--;
        return a;
    }

    /**
     * Pushes an item onto the top of this stack
     * @param item the item to append
     */
    @Override
    public void push(E item) {
        size++;
        top=new Node(item,top);
    }
}