import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;


import java.time.Duration;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;


public class HttpRoute {

    ActorRef configStore;
    Http http;

    HttpRoute(ActorRef configStore, Http http) {
        this.configStore = configStore;
        this.http = http;
    }

    public static final Duration TIMEOUT = Duration.ofSeconds(5);
    public static final String URL_PARAMETR = "url";
    public static final String COUNT_PARAMETR = "count";

    CompletionStage<HttpResponse> fetch(String url) {
        return http.singleRequest(HttpRequest.create(url));
    }


    public Route GetHttpRoute() {
        return route((get(() ->
                parameter(URL_PARAMETR, url -> {
                    return parameter(COUNT_PARAMETR, countStr -> {
                        int count = Integer.parseInt(countStr);
                        if(count == 0) {
                            return completeOKWithFuture(
                                    fetch(url),
                                    Jackson.marshaller()
                            );
                        }
                        CompletionStage<HttpResponse> res = Patterns.ask(configStore, null, TIMEOUT)
                                .thenCompose(response -> {
                                    String server = String.valueOf(response);
                                    return fetch(server + "?/" +
                                            URL_PARAMETR + "=" + url
                                            + "&" +
                                            COUNT_PARAMETR + "=" + String.valueOf(count - 1));
                                });
                        return completeOKWithFuture(
                                res,
                                Jackson.marshaller()
                        );
                    });
                })
        )));
    }
}
