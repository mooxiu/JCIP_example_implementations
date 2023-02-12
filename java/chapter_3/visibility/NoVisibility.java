package chapter_3.visibility;

import javax.swing.plaf.TableHeaderUI;

/**
 * This is highly depend on your CPU, in my M1 Pro chip, it prints 42 very soon
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
