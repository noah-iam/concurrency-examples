import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/*
    find max using fork join pool
 */
public class MaxExampleFJP {
    public static void main(String[] args) {
        int[] arr = getNumberArray(10000000);
        Worker.THRESHOLDS = arr.length/Runtime.getRuntime().availableProcessors();
        System.out.println(Worker.THRESHOLDS);
        Worker worker = new Worker(arr,0,arr.length-1);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        pool.invoke(worker);
        int max = worker.compute();
        System.out.println(max);
    }

    private static int[] getNumberArray(long N) {
        int[] arr = new int[(int) N];
        for(int i = 0 ; i<N;i++){
            arr[i] = i;
        }
        return arr;
    }
}


class Worker extends RecursiveTask<Integer> {
    private  int[] arr ;
    private int leftIndex;
    private int rightIndex;
     static int THRESHOLDS;

    public Worker(int[] arr, int leftIndex, int rightIndex) {
        this.arr = arr;
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
    }

    @Override
    protected Integer compute() {
        if((rightIndex - leftIndex) > THRESHOLDS){
            int mid = (leftIndex + rightIndex)/2;
            System.out.println("compute using parallel processing");
            Worker worker1 = new Worker(arr,leftIndex,mid);
            Worker worker2 = new Worker(arr,mid+1,rightIndex);
            invokeAll(worker1,worker2);
            return Math.max(worker1.join(),worker2.join());
        }else{
            System.out.println("compute using serial processing");
          return  findMax(arr,leftIndex,rightIndex) ;
        }
    }

    private Integer findMax(int[] arr, int leftIndex, int rightIndex) {
        int max = Integer.MIN_VALUE;
        for(int i=leftIndex; i<=rightIndex; i++){
            if(max < arr[i]) max = arr[i];
        }
        return max;
    }
}