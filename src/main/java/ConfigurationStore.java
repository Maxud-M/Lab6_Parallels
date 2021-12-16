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

        ServerList(int[] ports) {
            this.ports = ports;
        }


        String getServer(int index) {
            return buildUrl(index);
        }

        private String buildUrl(int index) {
            return "127.0.0.1" + ':' + ports[index];
        }

        int size() {
            return ports.length;
        }
    }
}
