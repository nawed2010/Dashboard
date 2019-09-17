/**
 * Created by nawed2010 on 11/07/19.
 */
public class ThreadOddEven {


    public static void main (String args []) {

        Lock lock = new Lock();

        Thread even = new Thread (new Runnable () {
            @Override
            public void run () {

                for (int i = 0; i <= 100 ; i = i+2) {
                    synchronized (lock) {

                            System.out.println("Even Thread: "+i);
                            try {
                                //lock.flag = 1;
                                lock.notify();
                                if (i != 100){
                                    lock.wait();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                    }
                }
            }
        });

        Thread odd = new Thread(new Runnable() {
            @Override
            public void run () {
                for (int i = 1; i <= 100 ; i = i+2) {
                    synchronized (lock) {


                            try {
                                //Thread.sleep(1000);
                                System.out.println("Odd Thread: "+i);
                                //lock.flag = 0;
                                lock.notify();
                                if (i != 99){
                                    lock.wait();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                    }
                }
            }
        });

        odd.start();
        even.start();
    }
}
