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

    public void subscribe(String path) throws InterruptedException, KeeperException {
        List<String> servers = zoo.getChildren(path, this);
        for(String s: servers) {
            byte[] data = s.getBytes();
            System.out.println("data: " + new String(data));
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            try {
                List<String> servers = zoo.getChildren("/servers", this);
                int[] ports = new int[servers.size()];
                int itr = 0;
                for(String s: servers) {
                    int port = Integer.parseInt(new String(s.getBytes()));
                    ports[itr] = port;
                    itr++;
                }
                configStore.tell(new ConfigurationStore.ServerList(ports), ActorRef.noSender());
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
