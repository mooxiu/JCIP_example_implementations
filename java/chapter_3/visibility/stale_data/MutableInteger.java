package chapter_3.visibility.stale_data;

import utils.annotations.NotThreadSafe;

@NotThreadSafe
public class MutableInteger {
    private int value;
    public int get() {
        return value;
    }
    public void set(int value) {
        this.value = value;
    }
}
