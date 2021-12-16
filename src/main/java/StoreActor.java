import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Random;

public class StoreActor extends AbstractActor {
    ServerList servers;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ServerList.class, m -> {
                    servers = m;
                })
                .matchAny(m -> {
                    new Random().nextInt(servers.si);
                })
                .build();

    }


    public static class ServerList{
        private int[] ports;
        private String[] names;

        ServerList(int[] ports, String[] names) {
            this.ports = ports;
            this.names = names;
        }

        String buildUrl(int index) {
            return names[index] + ':' + ports[index];
        }

        int getPort(int index) {
            return ports[index];
        }

        String getServer(int index) {
            return names[index];
        }

        int size() {
            return ports.length;
        }
    }
}
