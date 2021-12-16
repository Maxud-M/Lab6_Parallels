import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ChildZNodeWatcher implements Watcher {

    private ActorRef configStore;

    ChildZNodeWatcher(ActorRef configStore) {
        this.configStore = configStore;
    }

    public void subscribe(ZooKeeper zoo, String path) throws InterruptedException, KeeperException {
        zoo.getChildren(path, this);

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            
        }
    }
}
