package org.acme.vertx;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/async")
public class EventResource {

    @Inject
    EventBus bus;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public Uni<String> greeting(String name) {
		System.out.println("name: " + name);
        return bus.<String>request("greeting", name)
                .onItem().transform(Message::body);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/blocking/{name}")
    public Uni<String> blockingEvent(String name) {
		System.out.println("blockingEvent - name: " + name);
        return bus.<String>request("blockingEvent", name)
                .onItem().transform(Message::body);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/sendAndForgetBlocking/{name}")
    public String sendAndForgetBlocking(String name) {
		System.out.println("send and forget blocking - name: " + name);
        bus.<String>send("blockingEvent", name);

		return "ok";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/sendAndForget/{name}")
    public String sendAndForget(String name) {
		System.out.println("send and forget - name: " + name);
        bus.<String>send("blockingFalse", name);

		return "ok";
    }
}
