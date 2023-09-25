package philosophers;

import java.util.concurrent.Semaphore;

public class Table {
	private int nbrOfChopsticks;
	private Semaphore[] chopstickSemaphores;

	public Table(int nbrOfSticks) {
		nbrOfChopsticks = nbrOfSticks;
		chopstickSemaphores = new Semaphore[nbrOfChopsticks];
		for (int i = 0; i < nbrOfChopsticks; i++) {
			chopstickSemaphores[i] = new Semaphore(1);
		}
	}

	public synchronized void getLeftChopstick(int n) throws InterruptedException {
		while (!chopstickSemaphores[n].tryAcquire()) {
			wait();
		}
	}

	public synchronized void getRightChopstick(int n) throws InterruptedException {
		int pos = (n + 1) % nbrOfChopsticks;
		while (!chopstickSemaphores[pos].tryAcquire()) {
			wait();
		}
	}

	public synchronized void releaseLeftChopstick(int n) {
		chopstickSemaphores[n].release();
		notifyAll();
	}

	public synchronized void releaseRightChopstick(int n) {
		int pos = (n + 1) % nbrOfChopsticks;
		chopstickSemaphores[pos].release();
		notifyAll();
	}

	public int getNumberOfChopsticks() {
		return nbrOfChopsticks;
	}
}

