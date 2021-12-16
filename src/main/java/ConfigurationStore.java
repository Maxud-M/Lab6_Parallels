import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;


import java.util.Random;

public class ConfigurationStore extends AbstractActor {
    ServerList servers;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ServerList.class, m -> {
                    servers = m;
                })
                .matchAny(m -> {
                    Random indexGenerator = new Random();
                    int randomIndex = indexGenerator.nextInt(servers.size());
                    sender().tell(servers.getServer(randomIndex), self());
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


        String getServer(int index) {
            return buildUrl(index);
        }

        private String buildUrl(int index) {
            return names[index] + ':' + ports[index];
        }

        int size() {
            return ports.length;
        }
    }
}
