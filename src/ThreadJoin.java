/** https://javarevisited.blogspot.com/2012/02/what-is-race-condition-in.html
 *
 * Join - https://www.geeksforgeeks.org/joining-threads-in-java/
 * Created by nawed2010 on 12/07/19.
 */
public class ThreadJoin {

    public static void main (String args[]) throws Exception {


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Inside Thread "+Thread.currentThread().getName());
        }
    };


        System.out.println("1 Inside Thread "+Thread.currentThread().getName());

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        System.out.println("2 Inside Thread "+Thread.currentThread().getName());
        Thread t2 = new Thread(runnable);
        System.out.println("3 Inside Thread "+Thread.currentThread().getName());


        t0.start();
        System.out.println("4 Inside Thread "+Thread.currentThread().getName());
        t1.start();
        System.out.println("5 Inside Thread "+Thread.currentThread().getName());
        t2.start();
        //t2.join();
        System.out.println("6 Inside Thread "+Thread.currentThread().getName());
    }
}
