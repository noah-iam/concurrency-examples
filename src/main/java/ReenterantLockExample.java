import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class IncrementCounterTask implements Runnable{
    static Lock reentrantLock = new ReentrantLock();
    private  static int counter =0;

    void incrementCounter(){
        counter++;
    }

    @Override
     public void run() {
      //  reentrantLock.lock();
        for (int i = 0 ; i<10; i++) {
            incrementCounter();
            System.out.println(counter);
        }
      //  reentrantLock.unlock();
    }
 }

public class ReenterantLockExample {
    public static void main(String[] args) throws InterruptedException {
        IncrementCounterTask task = new IncrementCounterTask();

        Thread t1 = new Thread(task);

        Thread t2 = new Thread(task);


        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }


}
