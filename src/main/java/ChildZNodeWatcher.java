import akka.actor.ActorRef;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ChildZNodeWatcher implements Watcher {


    ChildZNodeWatcher(ActorRef configStore) {
        
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
