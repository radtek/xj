package interview;

public class TestThread implements Runnable{
	
	private int count;
	
	
	
	public TestThread(int count) {
		super();
		this.count = count;
	}



	public void run() {
		System.out.println("this is testThread!"+count);
	}
} 
