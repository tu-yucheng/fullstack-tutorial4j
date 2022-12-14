package cn.tuyucheng.taketoday.concurrent.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WaitingWorker implements Runnable {
	private final List<String> outputScraper;
	private final CountDownLatch readyThreadCounter;
	private final CountDownLatch callingThreadBlocker;
	private final CountDownLatch completedThreadCounter;

	public WaitingWorker(List<String> outputScraper, CountDownLatch readyThreadCounter, CountDownLatch callingThreadBlocker, CountDownLatch completedThreadCounter) {
		this.outputScraper = outputScraper;
		this.readyThreadCounter = readyThreadCounter;
		this.callingThreadBlocker = callingThreadBlocker;
		this.completedThreadCounter = completedThreadCounter;
	}

	@Override
	public void run() {
		// Mark this thread as read / started
		readyThreadCounter.countDown();
		try {
			callingThreadBlocker.await();
			outputScraper.add("Counted down");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		completedThreadCounter.countDown();
	}
}