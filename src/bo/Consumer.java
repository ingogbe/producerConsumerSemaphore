package bo;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
    private String consumerName;
    private LinkedList<Content> list;
    private Semaphore sem;
    private Semaphore mutex;
    private int consumptions;
    
    public Consumer(String consumerName, LinkedList<Content> list, Semaphore sem, Semaphore mutex, int consumptions) {
        this.consumerName = consumerName;
        this.list = list;
        this.sem = sem;
        this.mutex = mutex;
        this.consumptions = consumptions;
    }
    
    public void run() {
    	try {
    		while (getConsumptions() > 0) {
    			getSem().acquire(1);
    			
                getMutex().acquire();
                consume();
                getMutex().release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void consume(){
    	Content c = getList().removeFirst();
        System.out.println("Consumer \"" + getConsumerName() + "\" read: " + c.getValue() + " from " + c.getProducer().getProducerName() + ". Consumptions left: " + getDecrementedConsumptions());
    }

	public String getConsumerName() {
		return this.consumerName;
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

	public int getConsumptions() {
		return consumptions;
	}
	
	public int getDecrementedConsumptions() {
		this.consumptions--;
		return consumptions;
	}
    
	
	
}
