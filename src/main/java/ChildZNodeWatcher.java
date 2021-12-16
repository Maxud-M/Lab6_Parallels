import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

public class ChildZNodeWatcher implements Watcher {

    private ActorRef configStore;
    private ZooKeeper zoo;

    ChildZNodeWatcher(ActorRef configStore, ZooKeeper zoo) {
        this.configStore = configStore;
        this.zoo = zoo;
    }

    public void subscribe(ZooKeeper zoo, String path) throws InterruptedException, KeeperException {
        zoo.getChildren(path, this);

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            List<String> s = zoo.getChildren("/servers", this);
        }
    }
}
