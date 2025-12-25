package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void getTest() {
        ArrayDeque<Integer> getTestDeque = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000; i = i + 1) {
            getTestDeque.addLast(i);
        }
        assertEquals(958, (int) getTestDeque.get(958));
    }

    @Test
    public void equalDequeTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<Integer>();
        ArrayDeque<Integer> testEqualDeque = new ArrayDeque<Integer>();
        for (int i = 1; i <= 20; i = i + 1) {
            testDeque.addLast(i);
            testEqualDeque.addLast(i);
        }
        assertTrue(testDeque.equals(testEqualDeque));
    }

    @Test
    public void trickyEqualDequeTest1() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<Integer>();
        ArrayDeque<Integer> testEqualDeque = new ArrayDeque<Integer>();
        for (int i = 1; i <= 20; i = i + 1) {
            testDeque.addLast(i);
        }
        for (int i = 20; i >= 1; i = i - 1) {
            testEqualDeque.addFirst(i);
        }
        assertTrue(testDeque.equals(testEqualDeque));
    }

    @Test
    public void trickyEqualDequeTest2() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<Integer>();
        ArrayDeque<Integer> testEqualDeque = new ArrayDeque<Integer>();
        for (int i = 5; i >= 1; i = i - 1) {
            testDeque.addFirst(i);
        }
        for (int i = 6; i <= 20; i = i + 1) {
            testDeque.addLast(i);
        }
        for (int i = 15; i >= 1; i = i - 1) {
            testEqualDeque.addFirst(i);
        }
        for (int i = 16; i <= 20; i = i + 1) {
            testEqualDeque.addLast(i);
        }
        assertTrue(testDeque.equals(testEqualDeque));
    }

    @Test
    public void nestedEqualDequeTest() {
        ArrayDeque<ArrayDeque> testDeque = new ArrayDeque<ArrayDeque>();
        ArrayDeque<Integer> testEqualDeque = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000; i = i + 1) {
            testEqualDeque.addLast(i);
        }
        for (int i = 0; i < 1000; i = i + 1) {
            testDeque.addLast(testEqualDeque);
        }
        ArrayDeque<ArrayDeque> testDeque2 = new ArrayDeque<ArrayDeque>();
        ArrayDeque<Integer> testEqualDeque2 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000; i = i + 1) {
            testEqualDeque2.addLast(i);
        }
        for (int i = 0; i < 1000; i = i + 1) {
            testDeque2.addLast(testEqualDeque2);
        }
        assertEquals(testDeque, testDeque2);
    }

    @Test
    public void nonEqualDequeTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<Integer>();
        ArrayDeque<Integer> testEqualDeque = new ArrayDeque<Integer>();
        for (int i = 1; i <= 20; i = i + 1) {
            testDeque.addLast(i);
        }
        for (int i = 20; i >= 1; i = i - 1) {
            testEqualDeque.addLast(i);
        }
        assertFalse(testDeque.equals(testEqualDeque));
    }

    @Test
    public void nullEqualDequeTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<Integer>();
        ArrayDeque<Integer> testEqualDeque = new ArrayDeque<Integer>();
        assertTrue(testDeque.equals(testEqualDeque));
    }

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {


        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {


        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    public void multipleParamTest() {
        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();
        ArrayDeque<Boolean> lld4 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addLast(3.14159);
        lld3.addFirst(true);
        lld4.addLast(false);

        String s = lld1.removeFirst();
        double d = lld2.removeLast();
        boolean b1 = lld3.removeLast();
        boolean b2 = lld4.removeFirst();

        assertEquals("string", s);
        assertEquals(3.14159, d, 0);
        assertTrue(b1);
        assertFalse(b2);
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        ArrayDeque<Integer> lld2 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld2.addFirst(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld2.removeLast(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld2.removeFirst(), 0.0);
        }
    }

    @Test
    public void iteratorTest() {
        ArrayDeque<Integer> lld = new ArrayDeque<Integer>();
        for (int i = 0; i < 200; i = i + 1) {
            lld.addLast(i);
        }

        Iterator<Integer> lldIterator = lld.iterator();
        int position = 0;
        while (lldIterator.hasNext()) {
            int i = lldIterator.next();
            assertEquals(position, i);
            position = position + 1;
        }
    }

    @Test
    public void iterableTest() {
        ArrayDeque<Integer> lld = new ArrayDeque<Integer>();
        for (int i = 0; i < 200; i = i + 1) {
            lld.addLast(i);
        }

        int position = 0;
        for (int i : lld) {
            assertEquals(position, i);
            position = position + 1;
        }
    }
}