import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.server.Route;

import static akka.http.javadsl.server.Directives.*;

public class HttpRoute {

    ActorRef configStore;
    ActorSystem system;

    HttpRoute(ActorRef configStore, ActorSystem system) {
        this.configStore = configStore;
        this.system = system;
    }


    public static final String URL_PARAMETR = "url";
    public static final String COUNT_PARAMETR = "count";

    public Route GetHttpRoute() {
        return route(get(() ->
                parameter(URL_PARAMETR, url -> {
                    parameter(COUNT_PARAMETR, count -> {
                        configStore.tell(null, ActorRef.noSender());


                    });
                })
                )
        );
    }
}
