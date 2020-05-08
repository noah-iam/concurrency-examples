 class ProdConExample {
    private static int token = 5;
    Object lock = new Object();
     public void producer() throws InterruptedException {
         synchronized (lock) {
             while (true) {
                 if (token == 10) {
                     System.out.println("Waiting to adding items... ");
                     lock.wait();
                 }
                else {
                     token++;
                     System.out.println(token);
                     System.out.println("added...");
                     lock.notify();
                 }
             }
         }
     }

     public void consumer() throws InterruptedException {
         synchronized (lock) {
             while (true) {
                 while (token == 0 ) {
                     System.out.println("Waiting to removing items ");
                     lock.wait();
                 }
                 token--;
                 System.out.println(token);
                 System.out.println("removed...");
                 lock.notify();
                 Thread.sleep(1000);
             }
         }
     }
 }
 public class NotifyWaitExample {
     public static void main(String[] args) throws InterruptedException {
         ProdConExample prodConExample = new ProdConExample();
         Thread t1 = new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     prodConExample.producer();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         });

         Thread t2 = new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     prodConExample.consumer();
                 } catch (InterruptedException e) {

                 }
             }
         });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
     }
 }


