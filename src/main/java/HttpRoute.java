import akka.http.javadsl.server.Route;

import static akka.http.javadsl.server.Directives.*;

public class HttpRoute {

    public static final String URL_PARAMETR = "url";
    public static final String COUNT_PARAMETR = "count";

    public static Route GetHttpRoute() {
        return route(get(() ->
                parameter(URL_PARAMETR, url -> {
                    parameter(COUNT_PARAMETR, count -> {
                        
                    });
                })
                )
        );
    }
}
