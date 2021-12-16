import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class StoreActor extends AbstractActor {
    ServerList servers;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ServerList.class, m -> {
                    servers = m;
                })
                .match(Integer.class)
                .build();
    }


    public static class ServerList{
        int[] ports;
        String[] names;

        ServerList(int[] ports, String[] names) {
            this.ports = ports;
            this.names = names;
        }

        String buildUrl(int index) {
            return names[index] + ':' + ports[index];
        }
    }
}
