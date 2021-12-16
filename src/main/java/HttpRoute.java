import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import org.asynchttpclient.AsyncHttpClient;


import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

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
                            HttpRequest req = HttpRequest.GET(url);
                            HttpResponse res = HttpResponse.create().
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
