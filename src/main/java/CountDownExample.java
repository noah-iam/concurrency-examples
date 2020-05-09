import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CountdownTask implements Runnable {

    private CountDownLatch countDownLatch;

    public CountdownTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;

    }

    @Override
    public void run() {
        try {
            System.out.println("Current Thread: " + Thread.currentThread());
            Thread.sleep(4000);
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class CountDownExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        CountdownTask countdownTask = new CountdownTask(countDownLatch);
        ExecutorService executorService =  Executors.newFixedThreadPool(6);
        for(int i =0; i<6;i++){
        executorService.submit(countdownTask);
        }
        countDownLatch.await();
        System.out.println("Service up !!");
        executorService.shutdown();
    }
}
