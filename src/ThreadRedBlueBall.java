import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nawed2010 on 11/07/19.
 */
public class ThreadRedBlueBall {

    public static void main (String args[]) throws Exception {

        AtomicInteger lock = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(100);

        Thread blue = new Thread (new Runnable () {
            @Override
            public void run () {
                synchronized (lock) {
                    while (lock.get() <= 100) {
                        try {

                            System.out.println(lock.get() +" BLUE BALL");
                            lock.getAndIncrement();
                            lock.notify();
                            latch.countDown();
                            lock.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread red = new Thread (new Runnable () {
            @Override
            public void run () {
                synchronized (lock) {
                    while (lock.get() <= 100) {
                        try {

                            System.out.println(lock.get() +" RED BALL");
                            lock.getAndIncrement();
                            lock.notify();
                            latch.countDown();
                            lock.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        blue.start();
        red.start();
        latch.await();

        blue.join();
        red.join();

        System.out.println("Completed");

    }
}
