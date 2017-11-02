package produceConsumerSemaphore;

public class Content {
	private int value;
	private Producer producer;
	
	public Content(int value, Producer producer){
		this.value = value;
		this.producer = producer;
	}

	public int getValue() {
		return value;
	}

	public Producer getProducer() {
		return producer;
	}
	
	
}
