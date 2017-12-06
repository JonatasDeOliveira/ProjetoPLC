package ourGame;

public class Counter implements Runnable {

	long counter;
	boolean isPaused;

	public Counter() {
		this.counter = 0;
		this.isPaused = false;
	}

	public void run() {
		long current = System.currentTimeMillis(), aux;
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

			if (!this.isPaused) {
				aux = System.currentTimeMillis();
				if (aux - current >= 1000) {
					this.counter++;
					current = System.currentTimeMillis();
				}
			} else {
				current = System.currentTimeMillis();
			}
		}

	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
}
