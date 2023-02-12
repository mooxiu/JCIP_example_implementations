package chapter_3.visibility.stale_data;

import utils.annotations.GuardedBy;
import utils.annotations.ThreadSafe;

@ThreadSafe
public class SynchronizedInteger {
    @GuardedBy("this") private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }
}
