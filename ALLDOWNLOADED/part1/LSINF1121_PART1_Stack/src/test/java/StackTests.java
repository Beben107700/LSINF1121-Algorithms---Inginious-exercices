import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class StackTests {

    @Test
    public void firstTest() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(2);
        assertEquals(new Integer(2), stack.pop());
    }

    @Test
    public void secondTest() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertNotEquals(new Integer(2), stack.pop());
    }

    @Test
    public void thirdTest() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(new Integer(3), stack.pop());
        assertEquals(new Integer(2), stack.pop());
        assertEquals(new Integer(1), stack.pop());
        stack.push(4);
        assertEquals(new Integer(4), stack.pop());
    }

    @Test
    public void SimpleTestPop() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        assertEquals(new Integer(1), stack.pop());
    }

    @Test
    public void SimpleTestDoublePop() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertEquals(new Integer(1), stack.pop());
    }

    @Test
    public void SimpleTestPeek() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        assertEquals(new Integer(1), stack.peek());
    }

    @Test
    public void TestPeek() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        assertEquals(new Integer(1), stack.peek());
        stack.push(2);
        assertEquals(new Integer(2), stack.peek());
        stack.push(3);
        assertEquals(new Integer(3), stack.peek());
        stack.pop();
        assertEquals(new Integer(2), stack.peek());
        stack.push(4);
        assertEquals(new Integer(4), stack.peek());
    }

    @Test
    public void TestEmpty() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.push(4);
        assertFalse(stack.empty());
    }

    @Test
    public void TestEmpty2() {
        Stack<Integer> stack = new MyStack<>();
        assertTrue(stack.empty());
    }

    @Test
    public void TestEmpty3() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        assertFalse(stack.empty());
    }

    /*@Test
    public void Exception1Test() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.pop();
        boolean a = false;
        try {
            stack.pop();
        }catch (EmptyStackException e) {
            assertTrue("Exception test", true);
            a=true;
        }
        if (a == false){
            assertFalse("Exception test", true);
        }
    }

    @Test
    public void Exception2Test() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.pop();
        boolean a =false;
        try {
            stack.peek();
        }catch (EmptyStackException e) {
            assertTrue("Exception test", true);
            a=true;
        }
        if (a == false){
            assertFalse("Exception test", true);
        }
    }

    @Test
    public void Exception3Test() {
        Stack<Integer> stack = new MyStack<>();
        boolean a =false;
        for(int i = 0; i < 100; i++){
            stack.push(1);
        }
        for(int i = 0; i < 100; i++){
            stack.pop();
        }
        try {
            stack.pop();

        }catch (EmptyStackException e) {
            assertTrue("Exception test", true);
            a=true;
        }
        if ( !a ){
            assertFalse("Exception test", true);
        }
    }

    @Test
    public void Exception4Test() {
        Stack<Integer> stack = new MyStack<>();
        boolean a =false;
        for(int i = 0; i < 100; i++){
            stack.push(1);
        }
        for(int i = 0; i < 100; i++){
            stack.pop();
        }
        try {
            stack.peek();

        }catch (EmptyStackException e) {
            assertTrue("Exception test", true);
            a=true;
        }
        if (a == false){
            assertFalse("Exception test", true);
        }
    }

    */

    @Test
    public void bcpEntreeEmptyTest() {
        Stack<Integer> stack = new MyStack<>();
        for(int i = 0; i < 100; i++){
            stack.push(1);
        }
        assertFalse(stack.empty());
    }

    @Test
    public void bcpEntreePeekTest() {
        Stack<Integer> stack = new MyStack<>();
        for(int i = 0; i < 100; i++){
            stack.push(1);
        }
        stack.push(3);
        assertEquals(new Integer(3), stack.peek());
    }

    @Test
    public void bcpEntreeEmptyTrueTest() {
        Stack<Integer> stack = new MyStack<>();
        for(int i = 0; i < 100; i++){
            stack.push(1);
        }
        for(int i = 0; i < 100; i++){
            stack.pop();
        }
        assertTrue(stack.empty());
    }




    @Test
    public void ReturnedValueEmptyTest() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.push(4);
        assertEquals(false,stack.empty());
    }

    @Test
    public void ReturnedValueTest() {
        Stack<String> stack = new MyStack<>();
        stack.push("1");
        assertEquals(true, stack.pop() instanceof String);
    }

    @Test(expected = EmptyStackException.class)
    public void ReturnedValueTest2() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        assertEquals(true, stack.pop() instanceof Integer);
        stack.peek();
    }

    @Test(expected = EmptyStackException.class)
    public void ReturnedValueTest3() {
        Stack<Integer> stack = new MyStack<>();
        stack.push(1);
        assertEquals(true, stack.peek() instanceof Integer);
        assertEquals(new Integer (1),stack.peek());
        stack.pop();
        stack.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void SimpleTestExceptionPop() {
        Stack<Integer> stack = new MyStack<>();
        stack.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void SimpleTestExceptionPeek() {
        Stack<Integer> stack = new MyStack<>();
        stack.peek();
    }
/*recommence à tester valeurs retournées car ingi fait passer une erreur que je ne détecte pas——————————————————————————————————————————————————————————*/
    @Test
    public void value1(){
        Stack<Integer> stack = new MyStack<>();
        for(int i=0;i<200;i++){
            stack.push(i);
        }
        assertEquals(new Integer(199),stack.peek());
    }

    @Test
    public void value2(){
        Stack<Integer> stack = new MyStack<>();
        for(int i=0;i<200;i++){
            stack.push(i);
            assertEquals(new Integer(i),stack.peek());
        }
    }

    @Test(expected = EmptyStackException.class)
    public void value3(){
        Stack<Integer> stack = new MyStack<>();
        for(int i=0;i<200;i++){
            stack.push(i);
        }
        for(int i=199;i>=0;i--){
            assertEquals(new Integer(i),stack.pop());
        }
        stack.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void value4(){
        Stack<Integer> stack = new MyStack<>();
        for(int i=0;i<200;i++){
            stack.push(i);
        }
        for(int i=199;i>=0;i--){
            assertEquals(new Integer(i),stack.pop());
        }
        stack.peek();
    }

    @Test
    public void value5(){
        Stack<Integer> stack = new MyStack<>();
        for(int i=0;i<200;i++){
            stack.push(i);
        }
        assertFalse(stack.empty());
        for(int i=199;i>=0;i--){
            assertEquals(new Integer(i),stack.pop());
        }
        assertTrue(stack.empty());
    }




}

