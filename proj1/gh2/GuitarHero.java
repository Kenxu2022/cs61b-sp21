package gh2;

import deque.Deque;
import deque.LinkedListDeque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        Deque<GuitarString> guitarStringObjectArray = new LinkedListDeque<>();
        for (int i = 0; i < 37; i = i + 1) {
            double exponent = (i - 24) / 12.0;
            double frequency = 440 * Math.pow(2, exponent);
            guitarStringObjectArray.addLast(new GuitarString(frequency));
        }

        while (true) {
            try {
                /* check if the user has typed a key; if so, process it */
                if (StdDraw.hasNextKeyTyped()) {
                    char key = StdDraw.nextKeyTyped();
                    int keyIndex = keyboard.indexOf(key);
                    GuitarString guitarStringObject = guitarStringObjectArray.get(keyIndex);
                    guitarStringObject.pluck();
                }

                /* compute the superposition of samples */
                double sample = 0.0;
                for (GuitarString i : guitarStringObjectArray) {
                    sample = sample + i.sample();
                }

                /* play the sample on standard audio */
                StdAudio.play(sample);

                /* advance the simulation of each guitar string by one step */
                for (GuitarString i : guitarStringObjectArray) {
                    i.tic();
                }
            }
            catch(Exception e) {
                System.out.println("This key does not have corresponding note!");
                continue;
            }
        }
    }
}
