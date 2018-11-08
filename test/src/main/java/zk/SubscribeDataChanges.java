package zk;


import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

public class SubscribeDataChanges {
    static final String CONNECT_ADDR = "172.18.1.140:2181";
    static final int SESSION_TIMEOUT = 50000;

    public static void main(String[] args) throws InterruptedException {

        ZkClient zkClient = new ZkClient(new ZkConnection(CONNECT_ADDR, SESSION_TIMEOUT));
        zkClient.subscribeDataChanges("/super1", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("变更节点为：" + s + "，变更数据为：" + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("删除的节点为：" + s);
            }
        });
        zkClient.createPersistent("/super1", "123");
        Thread.sleep(3000);
        zkClient.writeData("/super1", "456", -1);
        Thread.sleep(1000);
        zkClient.createPersistent("/test1/c2", "内容二");
        Thread.sleep(1000);
        zkClient.delete("/test1/c2");
        zkClient.createPersistent("/super1/c1", "789"); //不会被监控到
        zkClient.deleteRecursive("/super1");
        Thread.sleep(Integer.MAX_VALUE);
    }
}