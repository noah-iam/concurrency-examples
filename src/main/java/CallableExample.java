import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallableTask implements Callable<String> {

    private int value = 0;

    public CallableTask(int value) {
        this.value = value;
    }

    @Override
    public String call() throws InterruptedException {
        return "value: "+value ;
    }
}


public class CallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> value = executor.submit(new CallableTask(i));
            list.add(value);
        }
        for(Future future:list){
            if(future.isDone())
                System.out.println(future.get());
        }

      executor.shutdown();
    }
}
