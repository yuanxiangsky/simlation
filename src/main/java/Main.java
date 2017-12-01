import peer.Peer;
import util.Rand;


import java.util.*;


public class Main {

    private static final int  NETWORK_SIZE = 20;
    private static final int COMM_COUNT= 10000;
    public static void main(String[] args) {

        List<Peer> peerList = new LinkedList<Peer>();
        Rand rand = new Rand();

        // 网络初始化
        for(int i=0;i<NETWORK_SIZE;i++){
            Peer peer = new Peer();
            peer.id = i;
            peer.identity=0;
            peerList.add(peer);
        }
        System.out.println("Network size is:" + peerList.size());
        // 设置恶意节点
        for(int i=0;i<NETWORK_SIZE;){
            peerList.get(0).identity = 1;
            i = i+10;
            if(peerList.get(0).identity == 1){
                System.out.println("恶意节点有: " + 0);
            }
        }

        for(int i = 0;i < COMM_COUNT; i++){
            int request = Rand.getRandom(0,NETWORK_SIZE);
            int response = Rand.getRandom(0, NETWORK_SIZE);
            System.out.println("Start"+i+" ,Peer:" + request + "-------------->" + "Peer:" + response);
            peerList.get(request).requestFile(peerList.get(response));
        }

        // 计算节点 10 的信任度
        int calNode = 10;
        Set<Integer> communicationPeers = peerList.get(calNode).communicationPeers;
        HashMap<Integer,List<Integer>> hashMap = new HashMap<Integer, List<Integer>>();
        for (Iterator<Integer> it = communicationPeers.iterator(); it.hasNext(); ) {
            List<Integer> judge = new LinkedList<Integer>();
            int commPeer  = it.next();
            System.out.println("commicate Peer is: "+commPeer);
            Peer peer = peerList.get(commPeer);
            for(int i=0;i<peer.records.size();i++){
                String record = peer.records.get(i);
                String[] s = record.split(" ");
                if(s[1].equals(String.valueOf(calNode))){
                    judge.add(Integer.parseInt(s[2]));
                }
            }
            hashMap.put(commPeer,judge);
        }
        for(int i : hashMap.keySet()){
            System.out.print("Peer " + i + ", ");
            for(int j=0;j<hashMap.get(i).size();j++){
                System.out.print(hashMap.get(i).get(j)+" ");
            }
            System.out.println();
        }

    }
}
