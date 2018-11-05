package zk;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

public class SubscribeChildChanges {
    static final String CONNECT_ADDR = "172.18.1.140:2181";
    static final int SESSION_TIMEOUT = 50000;

    public static void main(String[] args) throws InterruptedException {
//InterruptedException        ZkClient zkClient = new ZkClient(new ZkConnection(CONNECT_ADDR, SESSION_TIMEOUT));
//        zkClient.subscribeChildChanges("/test1", (parentPath, currentChilds) -> {
//            System.out.println("parentPath：" + parentPath);
//            System.out.println("currentChilds：" + currentChilds);
//        });
//
//        Thread.sleep(3000);
//        zkClient.createPersistent("/test1");
//        Thread.sleep(1000);
//        zkClient.createPersistent("/test1/c1", "内容一");
//        Thread.sleep(1000);
//        zkClient.createPersistent("/test1/c2", "内容二");
//        Thread.sleep(1000);
//        zkClient.delete("/test1/c2");
//        Thread.sleep(1000);
//        zkClient.deleteRecursive("/super");
//        Thread.sleep(Integer.MAX_VALUE);
    }
}