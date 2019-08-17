/** https://www.cs.carleton.edu/faculty/dmusican/cs348/java_multi/
 * Created by nawed2010 on 11/07/19.
 */
public class Deadlock {

    public static void main(String args[]) {

        DeadLockClass deadlock = new DeadLockClass();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run () {
                deadlock.method1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run () {
                deadlock.method2();
            }
        });

        t1.start();
        t2.start();

    }



    public static class DeadLockClass {
        /*
     * This method request two locks, first String and then Integer
     */
        public void method1() {
            synchronized (String.class) {
                System.out.println("Aquired lock on String.class object by "+Thread.currentThread().getName());

                synchronized (Integer.class) {
                    System.out.println("Aquired lock on Integer.class object by "+Thread.currentThread().getName());
                }
            }
        }

        /*
     * This method also requests same two lock but in exactly
     * Opposite order i.e. first Integer and then String.
     * This creates potential deadlock, if one thread holds String lock
     * and other holds Integer lock and they wait for each other, forever.
     */
        public void method2() {
            synchronized (Integer.class) {
                System.out.println("Aquired lock on Integer.class object by "+Thread.currentThread().getName());

                synchronized (String.class) {
                    System.out.println("Aquired lock on String.class object by "+Thread.currentThread().getName());
                }
            }
        }


        //Solution ---
        /*
        If you have looked above code carefully then you may have figured out that real reason for deadlock is not multiple threads
        but the way they are requesting a lock, if you provide an ordered access then the problem will be resolved.
        https://javarevisited.blogspot.com/2018/08/how-to-avoid-deadlock-in-java-threads.html
         */
    }

}
