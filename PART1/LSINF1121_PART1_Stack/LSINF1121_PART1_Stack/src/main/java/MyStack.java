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


    public MyStack(){
        this.top=null;
        this.size=0;
    }
    /**
     * Tests if this stack is empty
     */
    @Override
    public boolean empty() {
        return (this.size == 0);
    }

    /**
     * Looks at the object at the top of this stack
     * without removing it from the stack
     */
    @Override
    public E peek() throws EmptyStackException {
        if(this.size == 0) throw new EmptyStackException();
        return this.top.item;
    }

    /**
     * Removes the object at the top of this stack
     * and returns that object as the value of this function
     */
    @Override
    public E pop() throws EmptyStackException {
        if(this.size == 0) throw new EmptyStackException();
        E retval = this.top.item;
        this.top = this.top.next;
        this.size = this.size-1;
        return retval;
    }

    /**
     * Pushes an item onto the top of this stack
     * @param item the item to append
     */
    @Override
    public void push(E item) {
        this.size = this.size+1;
        Node<E> newtop = new Node(item, this.top);
        this.top = newtop;

    }
}
