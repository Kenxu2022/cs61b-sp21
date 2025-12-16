package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> numberList = new AList<Integer>();
        AList<Double> timeList = new AList<Double>();
        for (int i = 1000; i <= 128000000; i = i * 2){
            numberList.addLast(i);
        }

        for (int seq = 0; seq < numberList.size(); seq += 1){
            AList<Integer> calcList = new AList<Integer>();
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < numberList.get(seq); i++) {
                calcList.addLast(1);
            }
            double elapsedTime = sw.elapsedTime();
            timeList.addLast(elapsedTime);
        }
        printTimingTable(numberList, timeList, numberList);
    }
}
