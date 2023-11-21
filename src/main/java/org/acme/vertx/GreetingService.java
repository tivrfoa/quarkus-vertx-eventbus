package org.acme.vertx;

import io.quarkus.vertx.ConsumeEvent;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class GreetingService {
	static AtomicInteger qt = new AtomicInteger();
	static long start = 0;
	static final int SLEEP_MS = 3000;

	@ConsumeEvent("greeting")
	public String consume(String name) {
		return name.toUpperCase();
	}

	@ConsumeEvent(value = "blockingEvent", blocking = true)
	public String blockingEvent(String name) {
		int n = qt.addAndGet(1);
		if (n == 1) {
			start = System.currentTimeMillis();
		}
		try {
			if (n % 3 == 0)
				Thread.sleep(SLEEP_MS);
			else
				Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - " + name.toUpperCase() + " - " + n);
		if (name.equals("end")) {
			long elapsed = System.currentTimeMillis() - start;
			System.out.println("Time elapsed: " + elapsed);
		}
		return name.toUpperCase();
	}

	@ConsumeEvent("blockingFalse")
	public void blockingFalse(String name) {
		int n = qt.addAndGet(1);
		if (n == 1) {
			start = System.currentTimeMillis();
		}
		try {
			if (n % 3 == 0)
				Thread.sleep(SLEEP_MS);
			else
				Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - " + name.toUpperCase() + " - " + n);
		if (name.equals("end")) {
			long elapsed = System.currentTimeMillis() - start;
			System.out.println("Time elapsed: " + elapsed);
		}
	}
}
