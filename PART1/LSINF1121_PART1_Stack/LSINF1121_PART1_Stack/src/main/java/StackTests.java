import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTests
{
    @Test
    public void firstTest() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.push(1);
        assertEquals(new Integer(1), stack.pop());
    }

    @Test
    public void secondTest() {
        Stack<String> stack = new MyStack<String>() ;
        String[] strings = new String[5];

        for(int i = 0; i<5; i++){
            strings[i]=Integer.toString(i);
            stack.push(strings[i]);
        }
        for(int i = 4; i>=0; i--){
            assertEquals(strings[i], stack.pop());
        }

    }
}
