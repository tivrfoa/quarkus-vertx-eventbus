# Vert.x communication using EventBus

https://stackoverflow.com/questions/63628557/vertx-how-to-make-eventbus-request-to-wait-for-consumer-message-reply

```java
// how about decoupling the response to a different bus-address e.g "previewdataresponse":

// Verticle A.)

bus.<JsonObject>request("previewdata", m ,this::handle);
// show error if reply is not success

bus.<JsonObject>consumer("previewdataresponse", this::handleResponse);

public void handleResponse(Message<JsonObject> message) {
    // do something with the reponse
}

// Verticle B.)

bus.<JsonObject>consumer("previewdata", this::getPreviewData);

public void getPreviewData(Message<JsonObject> message) {
     JsonObject json = message.body();
     //...
     message.reply(json); // acknowledge that you received the message
     try {
         Thread.sleep(40000);
         bus.<JsonObject>send("previewdataresponse", json);
     }catch (Exception e) {
        // TODO: handle exception
     }
}
```
