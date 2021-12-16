import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class StoreActor extends AbstractActor {
    StoreMessage

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(HashMap.class, m -> {

                })
                .build();
    }


    public static class StoreMessage{
        int[] ports;
        String[] server;
    }
}
