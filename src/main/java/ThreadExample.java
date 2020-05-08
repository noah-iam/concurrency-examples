import java.util.stream.IntStream;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
       Thread t1 =  new Thread(new Task());
       Thread t2 =  new Thread(new Task2());
       t1.setName("Thread 1");
       t2.setName("Thread 2");
       t1.start();
       t2.start();
       t1.join();
       //t2.join();
        System.out.println("Finishing the Task" + Thread.activeCount());
    }
}

class Task implements Runnable{

    public void run() {
        IntStream.rangeClosed(1,10).forEach(System.out::println);
    }
}

class Task2 implements Runnable{

    public void run() {
        IntStream.rangeClosed(1,5000).forEach(System.out::println);
    }
}
