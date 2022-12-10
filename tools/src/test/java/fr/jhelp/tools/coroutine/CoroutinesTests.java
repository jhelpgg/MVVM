package fr.jhelp.tools.coroutine;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import fr.jhelp.tools.Tools;

public class CoroutinesTests {
    @Test
    public void launchDefault() {
        final AtomicInteger counter = new AtomicInteger(0);
        final AtomicInteger maximum = new AtomicInteger(0);
        final Runnable action =
                () -> {
                    synchronized (counter) {
                        final int value = counter.incrementAndGet();
                        System.out.println("ENTER : " + value);
                        maximum.set(Math.max(maximum.get(), value));
                    }

                    Tools.sleep(1024L);

                    synchronized (counter) {
                        final int value = counter.getAndDecrement();
                        System.out.println("EXIT : " + value);
                    }
                };

        for (int time = 0; time < 10; time++) {
            Coroutines.launch(action);
        }

        Tools.sleep(2048);
        Assert.assertEquals(0, counter.get());
        Assert.assertEquals(10, maximum.get());
    }

    @Test
    public void launchIO() {
        final AtomicInteger counter = new AtomicInteger(0);
        final AtomicInteger maximum = new AtomicInteger(0);
        final Runnable action =
                () -> {
                    synchronized (counter) {
                        final int value = counter.incrementAndGet();
                        System.out.println("ENTER : " + value);
                        maximum.set(Math.max(maximum.get(), value));
                    }

                    Tools.sleep(1024L);

                    synchronized (counter) {
                        final int value = counter.getAndDecrement();
                        System.out.println("EXIT : " + value);
                    }
                };

        for (int time = 0; time < 10; time++) {
            Coroutines.launchIO(action);
        }

        Tools.sleep(2048);
        Assert.assertEquals(0, counter.get());
        Assert.assertEquals(10, maximum.get());
    }
}
