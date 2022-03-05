/**
 * Created by nawed2010 on 27/09/19.
 */

/**
 * In case of class level locking, only one thread will execute the method. Others have to wait
 * even though object in both the thread are different. Whereas for object level lock, same method can
 * run in parallel if both are working on different object.
 *
 * Each object has just one lock(Monitor) -- it means at one time only one thread will have access
 *
 */
public class ClassVsObjectLock implements Runnable {

    @Override
    public void run() {
        Lock();
    }
    public void Lock() {
        System.out.println(Thread.currentThread().getName());
        synchronized(this) {
            System.out.println("in block " + Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("in block " + Thread.currentThread().getName() + " end");
        }
    }

    public static void main(String[] args) {
        ClassVsObjectLock b1 = new ClassVsObjectLock();
        Thread t1 = new Thread(b1);
        Thread t2 = new Thread(b1);
        ClassVsObjectLock b2 = new ClassVsObjectLock();
        Thread t3 = new Thread(b2);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t1.start();
        t2.start();
        t3.start();
    }

}
