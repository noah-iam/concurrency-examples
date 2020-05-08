import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class WaitSignalTask {
    Lock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();

     public void producer() throws InterruptedException {
         while(true) {
             reentrantLock.lock();
             System.out.println("Hello, I am in producer and I am waiting here for consumer to consume");
             condition.await();
             reentrantLock.unlock();
         }
    }

     public void consumer() throws InterruptedException {
         while(true) {
             reentrantLock.lock();
             System.out.println("Hello, I am in consumer");
             condition.signal();
             System.out.println("Hello, I am in consumer.. I have given signal to producer to produce");
             reentrantLock.unlock();
             Thread.sleep(1200);

         }
    }
}


public class WaitSignalExample {
    public static void main(String[] args) throws InterruptedException {

        WaitSignalTask waitSignalExample = new WaitSignalTask();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitSignalExample.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitSignalExample.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
