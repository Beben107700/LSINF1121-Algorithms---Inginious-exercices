import org.junit.Test;

import java.util.EmptyStackException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StackTests
{
    private Object EmptyStackException;

    @Test
    public void firstTest() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.push(1);
        assertEquals(new Integer(1), stack.pop());
    }

    @Test
    public void secondTest() {
        Stack<Integer> stack = new MyStack<Integer>();

        stack.push(1);
        stack.push(2);
        assertEquals(new Integer(2), stack.pop());
        assertTrue(1 == stack.pop());

    }
    /*
    Un test avec une liste de taille 2exp16
    Push, pop normal (OK)
    Emptystack exception (les deux)
    Push, pop, repush, repop, ...
     */
    @Test(expected = java.util.EmptyStackException.class)
    public void emptyTest() {
        Stack<String> stack = new MyStack<String>();
        stack.pop();
    }
    @Test(expected = java.util.EmptyStackException.class)
    public void emptyTesttwo() {
        Stack<String> stack = new MyStack<String>();
        stack.peek();
    }
    @Test(expected = java.util.EmptyStackException.class)
    public void emptyTest2() {
        Stack<String> stack = new MyStack<String>();
        stack.push("String");
        stack.pop();
        stack.pop();
    }
    @Test
    public void fullshit(){
        //Julien L m'a donné l'indice sur ce test, je n'avais pas pensé à le faire.
        Stack<Integer> stack = new MyStack<Integer>();
        for(long i = 0; i< Math.pow(2,16); i++){
            stack.push(3);
        }
        stack.push(4);
        assertFalse(stack.empty());
        assertEquals(new Integer(4), stack.pop());
        assertEquals(new Integer(3), stack.pop());
        assertFalse(stack.empty());


    }
}