import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CyclicBarrierTask implements Runnable {
    CyclicBarrier barrier;
    int count ;

    public CyclicBarrierTask(CyclicBarrier barrier, int count) {
        this.barrier = barrier;
        this.count = count;
    }

    @Override
    public void run() {
        doTask();
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void doTask() {
        System.out.println("Doing Something!!" + count);

    }
}

public class CyclicBarrierExample {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("All Task Finished!!!");
            }
        });
        ExecutorService service = Executors.newFixedThreadPool(2);

        for( int i=0; i<2; i++){
            service.submit(new CyclicBarrierTask(barrier,i));
        }

        System.out.println(" Number of Threads are waiting : "+barrier.getNumberWaiting());
        System.out.println(" Number of Parties : "+barrier.getParties());
        System.out.println(" is Broken : "+barrier.isBroken());

        barrier.await();

        System.out.println("Task finished");


    }
}
