package counter;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private static final Counter INSTANCE;

    static {
        INSTANCE = new Counter();
    }

    private Counter() {}

    public static Counter getInstance(){
        return INSTANCE;
    }

    private static final AtomicInteger counter = new AtomicInteger();

    public static int getCounter() {
        return counter.get();
    }

    public static int changeCounter(int delta) {
        return counter.addAndGet(delta);
    }

    public static void clearCounter() {
        counter.set(0);
    }

}
