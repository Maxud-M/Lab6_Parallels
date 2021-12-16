import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;


import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;
import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.get;

public class HttpRoute {

    ActorRef configStore;
    ActorSystem system;

    HttpRoute(ActorRef configStore, ActorSystem system) {
        this.configStore = configStore;
        this.system = system;
    }

    public static final Duration TIMEOUT = Duration.ofSeconds(5);
    public static final String URL_PARAMETR = "url";
    public static final String COUNT_PARAMETR = "count";

    public Route GetHttpRoute() {
        return route(get(() ->
                parameter(URL_PARAMETR, url -> {
                    parameter(COUNT_PARAMETR, countStr -> {
                        int count = Integer.parseInt(countStr);
                        if(count == 0) {
                            //execute http get request to url
                        }
                        Patterns.ask(configStore, null, TIMEOUT)
                                .thenCompose(response -> {
                                    String server = String.valueOf(response);
                                });


                    });
                })
                )
        );
    }
}
