import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class Main {

    public static final String ZOOKEEPER_SERVER = "127.0.0.1:2181";
    public static final int SESSION_TIMEOUT = 2000;
    public static final String PORT = "8080";


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        Object lock = new Object();

        ActorSystem system = ActorSystem.create();
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        ActorRef configStore = system.actorOf(Props.create(ConfigurationStore.class));
        Http http = Http.get(system);
        Watcher connectionWatcher = we -> {
            if (we.getState() == Watcher.Event.KeeperState.SyncConnected) {
                System.out.println("Connected to Zookeeper in " + Thread.currentThread().getName());
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        };
        ZooKeeper zoo = new ZooKeeper(ZOOKEEPER_SERVER, SESSION_TIMEOUT, connectionWatcher);
        zoo.create("/servers/s2", PORT.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        ChildZNodeWatcher watcher = new ChildZNodeWatcher(configStore, zoo);
        watcher.subscribe("/servers");

        HttpRoute httpRoute = new HttpRoute(configStore, http);
        Flow<HttpRequest, HttpResponse, NotUsed> flowRoute = httpRoute.GetHttpRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                flowRoute,
                ConnectHttp.toHost("localhost", 8082),
                materializer
        );

        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }
}
