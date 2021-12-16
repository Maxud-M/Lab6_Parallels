import akka.http.javadsl.server.Route;

import static akka.http.javadsl.server.Directives.*;

public class HttpRoute {

    

    public static Route GetHttpRoute() {
        return route(get(() ->
                parameter()
                )
        );
    }
}
