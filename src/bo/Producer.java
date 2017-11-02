package bo;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Producer extends Thread {
	private String producerName;
	private LinkedList<Content> list;
	private Semaphore sem;
	private Semaphore mutex;
	private int productions;
	
	public Producer(String producerName, LinkedList<Content> list, Semaphore sem, Semaphore mutex, int productions){
		this.producerName = producerName;
		this.list = list;
		this.sem = sem;
		this.mutex = mutex;
		this.productions = productions;
	}
	
    public void run() {
        try {
            while (getProductions() > 0) {
                getMutex().acquire();
                produce();
                getMutex().release();
                
                getSem().release(1);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void produce(){
    	 Content c = new Content(getDecrementedProductions(), this);
         getList().add(c);
         System.out.println("Producer \"" + getProducerName() + "\" write: " + c.getValue());
    }

	public LinkedList<Content> getList() {
		return list;
	}

	public Semaphore getSem() {
		return sem;
	}

	public Semaphore getMutex() {
		return mutex;
	}

	public String getProducerName() {
		return producerName;
	}

	public int getProductions() {
		return productions;
	}
	
	public int getDecrementedProductions() {
		this.productions--;
		return productions;
	}
    
    
    
}