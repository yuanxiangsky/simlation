package peer;


import util.Rand;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Peer {

    public int id;

    /*
     服务等级 1 2 3 4 5 越高越好， 善意节点反馈4-5 ， 恶意节点返回1 2 3
     0 善意节点
     1 恶意节点
     */
    public int identity;

    public int trust;

    public Set<Integer> communicationPeers = new HashSet<Integer>();

    public List<String> records = new LinkedList<String>();


    public void requestFile(Peer peer){
        communicationPeers.add(peer.id);
        peer.communicationPeers.add(this.id);
        int response = peer.responseFile();
        String record = "";
        if(this.identity == 0) {
            record = this.id + " " + peer.id + " " + response;
        }else{
            record = this.id + " " + peer.id + " " + -response;
        }
        records.add(record);
    }


    public int responseFile(){
        Rand rand = new Rand();
        if(this.identity == 0){
            return Rand.getRandom(4,10)+1;
        }else{
           return Rand.getRandom(0,6)+1;
        }
    }
}
