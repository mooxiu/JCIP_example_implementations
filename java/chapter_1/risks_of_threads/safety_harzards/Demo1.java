package chapter_1.risks_of_threads.safety_harzards;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        // use unsafe sequence
        UnsafeSequence s1 = new UnsafeSequence();
        SequenceThread t1 = new SequenceThread(s1, 1000);
        SequenceThread t2 = new SequenceThread(s1, 1000);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(s1.getValue()); // suppose to be not equal to 2000
        // use safe sequence implemented by atomic
        SafeSequenceWithAtomic s2 = new SafeSequenceWithAtomic();
        var t3 = new SequenceThread(s2, 1000);
        var t4 = new SequenceThread(s2, 1000);
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(s2.getValue()); // suppose to be equal to 2000

        // use safe sequence
        SafeSequence s3 = new SafeSequence();
        var t5 = new SequenceThread(s3, 1000);
        var t6 = new SequenceThread(s3, 1000);
        t5.start();
        t6.start();
        t5.join();
        t6.join();
        System.out.println(s3.getValue()); // suppose to be equal to 2000
    }
}

class SequenceThread extends Thread {
    MySequence sequence;
    int addCount;

    public SequenceThread(MySequence sequence, int addCount) {
        this.sequence = sequence;
        this.addCount = addCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < addCount; i++) {
            sequence.getNext();
        }
    }
}

class UnsafeSequence implements MySequence {
    private int value;

    public int getNext() {
        return value++;
    }

    public int getValue() {
        return value;
    }
}

class SafeSequenceWithAtomic implements MySequence {
    private AtomicInteger value = new AtomicInteger(0);

    public int getNext() {
        return value.incrementAndGet();
    }

    public int getValue() {
        return value.get();
    }
}

// implemented by the book using synchronized
class SafeSequence implements MySequence {
    private int value;

    @Override
    public synchronized int getNext() {
        return value++;
    }

    @Override
    public int getValue() {
        return value;
    }
}

interface MySequence {
    int getNext();

    int getValue();
}