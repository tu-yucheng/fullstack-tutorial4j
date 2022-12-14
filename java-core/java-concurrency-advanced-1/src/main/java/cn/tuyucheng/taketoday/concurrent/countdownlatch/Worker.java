package cn.tuyucheng.taketoday.concurrent.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
	private final List<String> outputScraper;
	private final CountDownLatch countDownLatch;

	public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
		this.outputScraper = outputScraper;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		// Do some work
		System.out.println("Doing some logic");
		outputScraper.add("Counted down");
		countDownLatch.countDown();
	}
}