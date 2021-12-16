import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        ActorSystem system = ActorSystem.create();
        ActorRef storeActor = system.actorOf(Props.create(ConfigurationStore.class));
        Http http = Http.get(system);
        

    }
}
