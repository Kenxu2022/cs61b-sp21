package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> alist1 = new AListNoResizing<>();
        BuggyAList<Integer> alist2 = new BuggyAList<>();
        for (int i = 4; i <= 6; i += 1){
            alist1.addLast(i);
            alist2.addLast(i);
        }
        for (int i = 6; i >= 4; i -= 1){
            int alist1Last = alist1.removeLast();
            int alist2Last = alist2.removeLast();
            assertEquals(alist1Last, alist2Last);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> bL = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                bL.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int bSize = bL.size();
                assertEquals(size, bSize);

            }
            else if (operationNumber == 2) {
                if (L.size() == 0) {
                    continue;
                }
                int lastVal = L.getLast();
                int bLastVal = bL.getLast();
                assertEquals(lastVal, bLastVal);
            }
            else {
                if (L.size() == 0) {
                    continue;
                }
                int removeLastVal = L.removeLast();
                int bRemoveLastVal = bL.removeLast();
                assertEquals(removeLastVal, bRemoveLastVal);
            }
        }
    }
}
