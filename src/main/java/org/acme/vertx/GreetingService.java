package org.acme.vertx;

import io.quarkus.vertx.ConsumeEvent;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {
	static int qt = 0;
	static final SLEEP_MS = 6000;

	@ConsumeEvent("greeting")
	public String consume(String name) {
		return name.toUpperCase();
	}

	@ConsumeEvent(value = "blockingEvent", blocking = true)
	public String blockingEvent(String name) {
		try {
			qt += 1;
			if (qt % 3 == 0)
				Thread.sleep(SLEEP_MS);
			else
				Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(name.toUpperCase());
		return name.toUpperCase();
	}

	@ConsumeEvent("blockingFalse")
	public void blockingFalse(String name) {
		try {
			qt += 1;
			if (qt % 3 == 0)
				Thread.sleep(SLEEP_MS);
			else
				Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(name.toUpperCase());
	}
}
