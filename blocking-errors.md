# Errors

```txt
2023-11-21 07:09:51,454 WARN  [io.ver.cor.imp.BlockedThreadChecker] (vertx-blocked-thread-checker) Thread Thread[#37,vert.x-eventloop-thread-1,5,main] has been blocked for 3082 ms, time limit is 2000 ms: io.vertx.core.VertxException: Thread blocked
	at java.base/java.lang.Thread.sleep0(Native Method)
	at java.base/java.lang.Thread.sleep(Thread.java:509)
	at org.acme.vertx.GreetingService.blockingFalse(GreetingService.java:37)
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
```


