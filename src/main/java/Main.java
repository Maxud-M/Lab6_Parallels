import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        ActorSystem system = ActorSystem.create();
        ActorMaterializer materializer = new ActorMaterializer(system);
        ActorRef configStore = system.actorOf(Props.create(ConfigurationStore.class));
        Http http = Http.get(system);
        HttpRoute httpRoute = new HttpRoute(configStore, http);
        Flow<HttpRequest, HttpResponse, NotUsed> flowRoute = httpRoute.GetHttpRoute().flow(system)

    }
}
