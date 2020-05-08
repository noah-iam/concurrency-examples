
public class VolatileExample {
    public static void main(String[] args) throws InterruptedException {
        VolatileTask.process();
        System.out.println(VolatileTask.counter);
    }

}


class VolatileTask {

    public  static int counter;

    public static void process() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<100;i++)
                ++counter;
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<100;i++)
                ++counter;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}