package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        int operationNum = 10000;
        AList<Integer> numberList = new AList<>();
        AList<Integer> countList = new AList<>();
        AList<Double> timeList = new AList<>();
        for (int i = 1000; i <= 128000; i = i * 2){
            numberList.addLast(i);
        }
        for (int seq = 0; seq < numberList.size(); seq += 1){
            countList.addLast(operationNum);
            SLList<Integer> calcList = new SLList<>();
            for(int i = 0; i < numberList.get(seq); i += 1){
                calcList.addLast(1);
            }
            Stopwatch sw = new Stopwatch();
            for(int i = 0; i < operationNum; i += 1){
                calcList.getLast();
            }
            double elapsedTime = sw.elapsedTime();
            timeList.addLast(elapsedTime);
        }
        printTimingTable(numberList, timeList, countList);
    }

}
