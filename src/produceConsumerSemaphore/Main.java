package produceConsumerSemaphore;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String [] args) {
    	
    	LinkedList<Content> list = new LinkedList<Content>();
        Semaphore sem = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);
    	
        new Producer("Tobias", list, sem, mutex, 5).start();
        new Producer("Nico", list, sem, mutex, 2).start();
        new Consumer("Alice", list, sem, mutex, 1).start();
        new Consumer("Bob", list, sem, mutex, 3).start();
        new Consumer("Toni", list, sem, mutex, 1).start();
        new Consumer("Rick", list, sem, mutex, 2).start();
    }
}