import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        TaskExample task = new TaskExample(600);
        forkJoinPool.invoke(task);
        task.compute();
    }
}

class TaskExample extends RecursiveAction {
    private int count;

    public TaskExample(int count){
        this.count = count;
    }
    @Override
    protected void compute() {
       if(count > 50) {
           System.out.println("Split the task parallel Stream! " + count);
           TaskExample taskExample1 = new TaskExample(count/2);
           TaskExample taskExample2 = new TaskExample(count/2);
           taskExample1.fork();
           taskExample2.fork();

       }else{
           System.out.println("No Need to do parallel Stream! " + count);
       }
    }
}
