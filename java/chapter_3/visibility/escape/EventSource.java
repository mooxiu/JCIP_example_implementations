package chapter_3.visibility.escape;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventSource extends Thread {
    private final BlockingQueue<EventListener> listeners = new LinkedBlockingQueue<>();

    // this is a thread repeatedly sends events to its latest listners
    public void run() {
        while (true) {
            try {
                listeners.take().onEvent(null);

            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void registerListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

}
