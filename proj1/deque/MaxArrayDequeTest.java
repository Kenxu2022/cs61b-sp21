package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    private static Comparator<Integer> getSmallestItemComparator() {
        return new SmallestItemComparator();
    }

    private static class SmallestItemComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return -(a - b);
        }
    }

    private static Comparator<String> getLongestStringComparator() {
        return new LongestStringComparator();
    }

    private static class LongestStringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void nullArrayTest() {
        Comparator<Integer> cmp = Comparator.naturalOrder();
        MaxArrayDeque<Integer> nullTestDeque = new MaxArrayDeque<>(cmp);
        assertNull(nullTestDeque.max());
    }

    @Test
    public void largestArrayTest() {
        Comparator<Integer> cmp = Comparator.naturalOrder();
        MaxArrayDeque<Integer> largestTestDeque = new MaxArrayDeque<>(cmp);
        for (int i = 10; i > 0; i = i - 1) {
            if (i % 2 == 1) {
                largestTestDeque.addFirst(i);
            }
            else {
                largestTestDeque.addLast(i);
            }
        }
        int largestItem = largestTestDeque.max();
        assertEquals(10, largestItem);
    }

    @Test
    public void longestStringTest() {
        Comparator<String> cmp = getLongestStringComparator();
        MaxArrayDeque<String> longestTestDeque = new MaxArrayDeque<>(cmp);
        String[] testStringArray = {"Across", "the", "Great", "Wall", "and", "we", "can", "reach", "every", "corner", "in", "the", "world", "!"};
        for (String s : testStringArray) {
            longestTestDeque.addLast(s);
        }
        assertEquals("Across", longestTestDeque.max());
    }

    @Test
    public void smallestArrayTest() {
        Comparator<Integer> cmp = getSmallestItemComparator();
        MaxArrayDeque<Integer> smallestTestDeque = new MaxArrayDeque<>(cmp);
        for (int i = 10; i > 0; i = i - 1) {
            if (i % 2 == 1) {
                smallestTestDeque.addFirst(i);
            }
            else {
                smallestTestDeque.addLast(i);
            }
        }
        int smallestItem = smallestTestDeque.max();
        assertEquals(1, smallestItem);
    }

    @Test
    public void trickyLargestArrayTest() {
        Comparator<Integer> cmp = getSmallestItemComparator();
        MaxArrayDeque<Integer> largestTestDeque = new MaxArrayDeque<>(cmp);
        for (int i = 10; i > 0; i = i - 1) {
            if (i % 2 == 1) {
                largestTestDeque.addFirst(i);
            }
            else {
                largestTestDeque.addLast(i);
            }
        }
        int largestItem = largestTestDeque.max(Comparator.naturalOrder());
        assertEquals(10, largestItem);
    }

    @Test
    public void trickySmallestArrayTest() {
        Comparator<Integer> cmp = Comparator.naturalOrder();
        MaxArrayDeque<Integer> smallestTestDeque = new MaxArrayDeque<>(cmp);
        for (int i = 10; i > 0; i = i - 1) {
            if (i % 2 == 1) {
                smallestTestDeque.addFirst(i);
            }
            else {
                smallestTestDeque.addLast(i);
            }
        }
        int smallestItem = smallestTestDeque.max(getSmallestItemComparator());
        assertEquals(1, smallestItem);
    }
}