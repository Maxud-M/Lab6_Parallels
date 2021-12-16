import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class StoreActor extends AbstractActor {
    ServerList

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ServerList.class, m -> {

                })
                .build();
    }


    public static class ServerList{
        int[] ports;
        String[] servers;

        ServerList(int[] ports, String[] server) {
            this.ports = ports;
            this.servers = servers;
        }

        
    }
}
