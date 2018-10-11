import java.util.concurrent.Semaphore;
public class producer_consumer {
	private static int count = 0;
	final Semaphore Full = new Semaphore(3);
	final Semaphore Empty = new Semaphore(0);
	final Semaphore mutex = new Semaphore(1);
	
	public static void main(String[] args) {
		producer_consumer test = new producer_consumer();
		new Thread(test.new Producer()).start();
		new Thread(test.new Consumer()).start();
		new Thread(test.new Producer()).start();
		new Thread(test.new Consumer()).start();
		new Thread(test.new Producer()).start();
		new Thread(test.new Consumer()).start();
		new Thread(test.new Producer()).start();
		new Thread(test.new Consumer()).start();
	}
	
	class Producer implements Runnable {  //extends Thread
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(300);//模拟线程
				}
				catch (InterruptedException e) {
					e.printStackTrace();// TODO: handle exception
				}
				try {
					Full.acquire();
					mutex.acquire();
					count++;
					System.out.println(Thread.currentThread().getName() + "producer produce, total count is" + count);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();// TODO: handle exception
				} 
				finally {
					mutex.release();
					Empty.release();
				}
			}
		}	
	}
	
	class Consumer implements Runnable {
		@Override
		public void run () {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(300);
				}
				catch (InterruptedException e1) {
					e1.printStackTrace();// TODO: handle exception
				}
				try {
					Empty.acquire();
					mutex.acquire();
					count--;
					System.out.println(Thread.currentThread().getName() + "consumer consume, total count is" + count);
				}
				catch (InterruptedException e) {
					e.printStackTrace();// TODO: handle exception
				}
				finally {
					mutex.release();
					Full.release();
				}
			}
		}
	}
}
