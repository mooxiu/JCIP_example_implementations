package chapter_3.visibility.escape;

import java.util.Date;

public class SafeListener {
    private final int num;
    private final EventListener listener;

    private SafeListener() {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
        num = 42;
    }

    public static SafeListener newInstance(EventSource s) {
        SafeListener safeListener = new SafeListener();
        s.registerListener(safeListener.listener);
        return safeListener;
    }

    private void doSomething(Event e) {
        if (num != 42) {
            System.out.println("Race condition detected at " + new Date());
        }
    }
}
