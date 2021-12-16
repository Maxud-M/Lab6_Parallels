import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class StoreActor extends AbstractActor {
    HashMap<String, Integer> StoreMes
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {

                })
                .build();
    }


    public static class StoreMessage{
        int[] ports;
        String server;

    }
}
