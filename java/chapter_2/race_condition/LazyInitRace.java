package chapter_2.race_condition;

import utils.annotations.NotThreadSafe;
import utils.helpers.HelpFunctions;

@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        LazyInitRace lr = new LazyInitRace();
        FailThread t1 = new FailThread(lr);
        FailThread t2 = new FailThread(lr);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(lr.getInstance().getValue()); // should be 3, but 2
    }
}

class FailThread extends Thread {
    LazyInitRace lr;

    public FailThread(LazyInitRace lr) {
        this.lr = lr;
    }

    @Override
    public void run() {
        System.out.printf("%d start ...%n", this.threadId());
        var ins = lr.getInstance();
        ins.addValue();
        System.out.println(ins.getValue());
        System.out.printf("%d end ...%n", this.threadId());
    }
}

class ExpensiveObject {
    private int value;

    public ExpensiveObject() {
        HelpFunctions.timeConsuming(10000L);
        value = 1;
    }

    public int getValue() {
        return value;
    }

    public void addValue() {
        value++;
    }
}
