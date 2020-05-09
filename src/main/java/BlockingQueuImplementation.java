import java.util.LinkedList;


public class BlockingQueuImplementation {
    public static void main(String[] args) {
        int limit =10;
        LinkedList<Integer> list = new LinkedList<>();
        BlockingQueue queue = new BlockingQueue(list,limit);

    }
}

// Structure of Blocking Queue

class BlockingQueue  {
    LinkedList<Integer> list ;
    int limit;
    public BlockingQueue(LinkedList<Integer> list, int limit) {
        this.list = list;
        this.limit = limit;
     }

     synchronized void addToQueue(int value) throws InterruptedException {
        while (list.size() == limit){
            wait();
        }
        list.add(value);
        notifyAll();
    }

     synchronized void DeQueue(int value) throws InterruptedException {
        while (list.size() == 0){
            wait();
        }
        list.remove(value);
        notifyAll();
    }

}
