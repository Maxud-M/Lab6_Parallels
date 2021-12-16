import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import org.apache.zookeeper.server.Request;


import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;


public class HttpRoute {

    ActorRef configStore;
    ActorSystem system;
    Http http;

    HttpRoute(ActorRef configStore, ActorSystem system, Http http) {
        this.configStore = configStore;
        this.system = system;
        this.http = http;
    }

    public static final Duration TIMEOUT = Duration.ofSeconds(5);
    public static final String URL_PARAMETR = "url";
    public static final String COUNT_PARAMETR = "count";

    CompletionStage<HttpResponse> fetch(String url) {
        return http.singleRequest()
    }


    public Route GetHttpRoute() {
        return route((get(() ->
                parameter(URL_PARAMETR, url -> {
                    parameter(COUNT_PARAMETR, countStr -> {
                        int count = Integer.parseInt(countStr);
                        if(count == 0) {
                            //execute http get request to url
                        }
                        http.singleRequest(HttpRequest.create(url));


                    });
                })
                )
        );
    }
}
