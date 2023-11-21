# blocking = true -> executor-thread

```sh
$ wrk -R5 -t1 -c50 -d5s http://localhost:8080/async/sendAndForgetBlocking/aa
Running 5s test @ http://localhost:8080/async/sendAndForgetBlocking/aa
  1 threads and 50 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   159.93ms   72.10ms 284.42ms   58.00%
    Req/Sec       -nan      -nan   0.00      0.00%
  50 requests in 6.26s, 3.91KB read
  Socket errors: connect 0, read 0, write 0, timeout 150
Requests/sec:      7.99
Transfer/sec:     639.38B
$ curl http://localhost:8080/async/sendAndForgetBlocking/end
ok
```

```sh
$ java -jar target/quarkus-app/quarkus-run.jar
__  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2023-11-21 09:14:17,066 INFO  [io.quarkus] (main) vertx-quickstart 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.5.2) started in 1.714s. Listening on: http://0.0.0.0:8080
2023-11-21 09:14:17,085 INFO  [io.quarkus] (main) Profile prod activated.
2023-11-21 09:14:17,085 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy-reactive, smallrye-context-propagation, vertx]
send and forget blocking - name: aa
send and forget blocking - name: aa
...
send and forget blocking - name: aa
send and forget blocking - name: aa
send and forget blocking - name: aa
executor-thread-44 - AA - 43
executor-thread-47 - AA - 44
executor-thread-36 - AA - 19
executor-thread-7 - AA - 17
executor-thread-6 - AA - 47
executor-thread-34 - AA - 26
executor-thread-19 - AA - 1
executor-thread-21 - AA - 4
executor-thread-17 - AA - 16
executor-thread-23 - AA - 31
executor-thread-54 - AA - 8
executor-thread-30 - AA - 40
executor-thread-51 - AA - 49
executor-thread-5 - AA - 32
executor-thread-22 - AA - 25
executor-thread-9 - AA - 13
executor-thread-2 - AA - 2
executor-thread-29 - AA - 38
executor-thread-49 - AA - 22
executor-thread-53 - AA - 10
executor-thread-25 - AA - 37
executor-thread-4 - AA - 46
executor-thread-31 - AA - 29
executor-thread-35 - AA - 35
executor-thread-33 - AA - 20
executor-thread-38 - AA - 7
executor-thread-13 - AA - 41
executor-thread-12 - AA - 14
executor-thread-45 - AA - 23
executor-thread-55 - AA - 28
executor-thread-1 - AA - 5
executor-thread-8 - AA - 50
executor-thread-3 - AA - 34
executor-thread-41 - AA - 11
executor-thread-14 - AA - 3
executor-thread-15 - AA - 6
executor-thread-52 - AA - 12
executor-thread-37 - AA - 9
executor-thread-10 - AA - 15
executor-thread-46 - AA - 18
executor-thread-16 - AA - 21
executor-thread-43 - AA - 24
executor-thread-48 - AA - 30
executor-thread-26 - AA - 33
executor-thread-27 - AA - 36
executor-thread-32 - AA - 27
executor-thread-18 - AA - 39
executor-thread-40 - AA - 42
executor-thread-42 - AA - 45
executor-thread-50 - AA - 48
send and forget blocking - name: end
executor-thread-42 - END - 51
Time elapsed: 11348
```

# blocking = false -> eventloop-thread

It uses the same thread, that's why it gets really slow if it blocks!

```sh
$ wrk -R5 -t1 -c50 -d5s http://localhost:8080/async/sendAndForget/aa
Running 5s test @ http://localhost:8080/async/sendAndForget/aa
  1 threads and 50 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   228.11ms    3.48ms 232.70ms   77.27%
    Req/Sec       -nan      -nan   0.00      0.00%
  44 requests in 6.26s, 3.44KB read
  Socket errors: connect 0, read 0, write 0, timeout 100
Requests/sec:      7.03
Transfer/sec:     562.43B
$ curl http://localhost:8080/async/sendAndForget/end
```

```txt
$ java -jar target/quarkus-app/quarkus-run.jar
__  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2023-11-21 09:19:02,633 INFO  [io.quarkus] (main) vertx-quickstart 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.5.2) started in 1.713s. Listening on: http://0.0.0.0:8080
2023-11-21 09:19:02,651 INFO  [io.quarkus] (main) Profile prod activated.
2023-11-21 09:19:02,652 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy-reactive, smallrye-context-propagation, vertx]
send and forget - name: aa
send and forget - name: aa
...
send and forget - name: aa
send and forget - name: aa
send and forget - name: aa
vert.x-eventloop-thread-1 - AA - 1
vert.x-eventloop-thread-1 - AA - 2
vert.x-eventloop-thread-1 - AA - 3
vert.x-eventloop-thread-1 - AA - 4
vert.x-eventloop-thread-1 - AA - 5
send and forget - name: end
2023-11-21 09:19:48,259 WARN  [io.ver.cor.imp.BlockedThreadChecker] (vertx-blocked-thread-checker) Thread Thread[#38,vert.x-eventloop-thread-1,5,main] has been blocked for 2833 ms, time limit is 2000 ms: io.vertx.core.VertxException: Thread blocked
	at java.base/java.lang.Thread.sleep0(Native Method)
	at java.base/java.lang.Thread.sleep(Thread.java:509)
	at org.acme.vertx.GreetingService.blockingFalse(GreetingService.java:49)
	at org.acme.vertx.GreetingService_ClientProxy.blockingFalse(Unknown Source)
	at org.acme.vertx.GreetingService_VertxInvoker_blockingFalse_7f16a65a2e4d5cfc1bfa7da799acc9e00f8bb794.invokeBean(Unknown Source)
	at io.quarkus.vertx.runtime.EventConsumerInvoker.invoke(EventConsumerInvoker.java:45)
	at io.quarkus.vertx.runtime.VertxEventBusConsumerRecorder$3$1.handle(VertxEventBusConsumerRecorder.java:162)
	at io.quarkus.vertx.runtime.VertxEventBusConsumerRecorder$3$1.handle(VertxEventBusConsumerRecorder.java:107)
	at io.vertx.core.impl.ContextInternal.dispatch(ContextInternal.java:277)
	at io.vertx.core.eventbus.impl.MessageConsumerImpl.dispatch(MessageConsumerImpl.java:177)
	at io.vertx.core.eventbus.impl.HandlerRegistration$InboundDeliveryContext.execute(HandlerRegistration.java:137)
	at io.vertx.core.eventbus.impl.DeliveryContextBase.next(DeliveryContextBase.java:80)
	at io.vertx.core.eventbus.impl.DeliveryContextBase.dispatch(DeliveryContextBase.java:43)
	at io.vertx.core.eventbus.impl.HandlerRegistration.dispatch(HandlerRegistration.java:98)
	at io.vertx.core.eventbus.impl.MessageConsumerImpl.deliver(MessageConsumerImpl.java:183)
	at io.vertx.core.eventbus.impl.MessageConsumerImpl.doReceive(MessageConsumerImpl.java:168)
	at io.vertx.core.eventbus.impl.HandlerRegistration.lambda$receive$0(HandlerRegistration.java:49)
	at io.netty.util.concurrent.AbstractEventExecutor.runTask(AbstractEventExecutor.java:173)
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:166)
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:470)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:566)
	at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997)
	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.base/java.lang.Thread.run(Thread.java:1583)

vert.x-eventloop-thread-1 - AA - 6
vert.x-eventloop-thread-1 - AA - 7
vert.x-eventloop-thread-1 - AA - 8
vert.x-eventloop-thread-1 - AA - 9
vert.x-eventloop-thread-1 - AA - 10
vert.x-eventloop-thread-1 - AA - 11
2023-11-21 09:19:58,260 WARN  [io.ver.cor.imp.BlockedThreadChecker] (vertx-blocked-thread-checker) Thread Thread[#38,vert.x-eventloop-thread-1,5,main] has been blocked for 2830 ms, time limit is 2000 ms: io.vertx.core.VertxException: Thread blocked
	at java.base/java.lang.Thread.sleep0(Native Method)
	...

vert.x-eventloop-thread-1 - AA - 12
vert.x-eventloop-thread-1 - AA - 13
vert.x-eventloop-thread-1 - AA - 14
vert.x-eventloop-thread-1 - AA - 15
vert.x-eventloop-thread-1 - AA - 16
vert.x-eventloop-thread-1 - AA - 17
2023-11-21 09:20:08,259 WARN  [io.ver.cor.imp.BlockedThreadChecker] (vertx-blocked-thread-checker) Thread Thread[#38,vert.x-eventloop-thread-1,5,main] has been blocked for 2825 ms, time limit is 2000 ms: io.vertx.core.VertxException: Thread blocked
	at java.base/java.lang.Thread.sleep0(Native Method)
	...

vert.x-eventloop-thread-1 - AA - 18
vert.x-eventloop-thread-1 - AA - 19
vert.x-eventloop-thread-1 - AA - 20
vert.x-eventloop-thread-1 - AA - 21
vert.x-eventloop-thread-1 - AA - 22
vert.x-eventloop-thread-1 - AA - 23
2023-11-21 09:20:18,262 WARN  [io.ver.cor.imp.BlockedThreadChecker] (vertx-blocked-thread-checker) Thread Thread[#38,vert.x-eventloop-thread-1,5,main] has been blocked for 2824 ms, time limit is 2000 ms: io.vertx.core.VertxException: Thread blocked
	at java.base/java.lang.Thread.sleep0(Native Method)
	...

vert.x-eventloop-thread-1 - AA - 24
vert.x-eventloop-thread-1 - AA - 25
vert.x-eventloop-thread-1 - AA - 26
vert.x-eventloop-thread-1 - AA - 27
vert.x-eventloop-thread-1 - AA - 28
vert.x-eventloop-thread-1 - AA - 29
2023-11-21 09:20:28,264 WARN  [io.ver.cor.imp.BlockedThreadChecker] (vertx-blocked-thread-checker) Thread Thread[#38,vert.x-eventloop-thread-1,5,main] has been blocked for 2822 ms, time limit is 2000 ms: io.vertx.core.VertxException: Thread blocked
	at java.base/java.lang.Thread.sleep0(Native Method)
	...

vert.x-eventloop-thread-1 - AA - 30
vert.x-eventloop-thread-1 - AA - 31
vert.x-eventloop-thread-1 - AA - 32
vert.x-eventloop-thread-1 - AA - 33
vert.x-eventloop-thread-1 - AA - 34
vert.x-eventloop-thread-1 - AA - 35
2023-11-21 09:20:38,265 WARN  [io.ver.cor.imp.BlockedThreadChecker] (vertx-blocked-thread-checker) Thread Thread[#38,vert.x-eventloop-thread-1,5,main] has been blocked for 2818 ms, time limit is 2000 ms: io.vertx.core.VertxException: Thread blocked
	at java.base/java.lang.Thread.sleep0(Native Method)
	...

vert.x-eventloop-thread-1 - AA - 36
vert.x-eventloop-thread-1 - AA - 37
vert.x-eventloop-thread-1 - AA - 38
vert.x-eventloop-thread-1 - AA - 39
vert.x-eventloop-thread-1 - AA - 40
vert.x-eventloop-thread-1 - AA - 41
2023-11-21 09:20:48,267 WARN  [io.ver.cor.imp.BlockedThreadChecker] (vertx-blocked-thread-checker) Thread Thread[#38,vert.x-eventloop-thread-1,5,main] has been blocked for 2816 ms, time limit is 2000 ms: io.vertx.core.VertxException: Thread blocked
	at java.base/java.lang.Thread.sleep0(Native Method)
	...

vert.x-eventloop-thread-1 - AA - 42
vert.x-eventloop-thread-1 - AA - 43
vert.x-eventloop-thread-1 - AA - 44
vert.x-eventloop-thread-1 - AA - 45
vert.x-eventloop-thread-1 - AA - 46
vert.x-eventloop-thread-1 - AA - 47
2023-11-21 09:20:58,268 WARN  [io.ver.cor.imp.BlockedThreadChecker] (vertx-blocked-thread-checker) Thread Thread[#38,vert.x-eventloop-thread-1,5,main] has been blocked for 2812 ms, time limit is 2000 ms: io.vertx.core.VertxException: Thread blocked
	at java.base/java.lang.Thread.sleep0(Native Method)
	...

vert.x-eventloop-thread-1 - AA - 48
vert.x-eventloop-thread-1 - AA - 49
vert.x-eventloop-thread-1 - AA - 50
vert.x-eventloop-thread-1 - END - 51
Time elapsed: 85046
```
