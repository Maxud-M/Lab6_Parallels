import akka.actor.ActorRef;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ChildZNodeWatcher implements Watcher {


    public static void subscribe(ZooKeeper zoo) {
        zoo.getChildren()
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
