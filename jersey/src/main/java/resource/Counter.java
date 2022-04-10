package resource;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private LocalDateTime date = LocalDateTime.now();
    private static AtomicInteger counter = new AtomicInteger();

//    public String getDate(){
//        return date.toString();
//    }

    public int getCounter() {
        return counter.get();
    }

    public int changeCounter(int delta) {
        return counter.addAndGet(delta);
    }

    public void clearCounter() {
        counter.set(0);
    }

}
