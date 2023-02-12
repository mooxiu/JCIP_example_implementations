package chapter_3.visibility.escape;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;
import java.util.Locale;

public class ThisEscape {
    private final int num;


    // this is a constructor
    // why this implicit class instance escape the outer class: https://www.javaspecialists.eu/archive/Issue192-Implicit-Escape-of-this.html
    public ThisEscape(EventSource source) {
        source.registerListener(
                new EventListener() {
                    public void onEvent(Event e) {
                        doSomething(e);
                    }
                });
        // doSomething can be called while ThisEscape is not fully constructed
        num = 42;
    }

    private void doSomething(Event e) {
        if (num != 42) {
            System.out.println("Race condition detected at " + new Date());
        }
        //We should never allow references to our objects to escape before all the final fields have been set, otherwise we can cause race conditions.
    }

    public static void main(String[] args) {
        EventSource es = new EventSource();
        es.start();
        while (true) {
            new ThisEscape(es);
        }
    }
}
