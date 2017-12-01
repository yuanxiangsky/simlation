import Jama.Matrix;
import util.Rand;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class MainTrust1 {



    public double[][] initT(int peerNum){  //列向量
        double[][] T = new double[peerNum][1];
        for(int i=0;i<peerNum;i++){
            T[i][0] = 0.5;
        }
        Matrix matrix = new Matrix(T);
        System.out.println("初始信任值为");
        matrix.transpose().print(2,3);
        return T;
    }

    /**
     * 初始化恶意节点
     * @param peerNum
     * @param bad
     * @return
     */
    public Set<Integer> initBadSet(int peerNum, int bad){
        Random random = new Random(System.currentTimeMillis());
        Set<Integer> badSet = new HashSet<Integer>();
        for(int i=0;i<bad;i++){
            int next = random.nextInt(peerNum);
            while(badSet.contains(next)) {
                next = random.nextInt(peerNum);
            }
            badSet.add(next);
        }
        System.out.println("恶意节点集合为 : ");
        for(int i: badSet){
            System.out.print(" "+ i+ " ");
        }
        System.out.println();
        return badSet;
    }


    public double[][] initTL(int peerNum, Set<Integer> badSet){
        double[][] TL = new double[peerNum][peerNum];


        for(int i=0; i<peerNum; i++){
            for(int j=0; j<peerNum; j++){
                if(!badSet.contains(i)){
                    if(!badSet.contains(j)){   //good ----> good
                        TL[i][j] = Rand.nextDouble(0.7,1.0);

                    }else{                    // good ----> bad
                        TL[i][j] =  Rand.nextDouble(0.0,0.4);
                    }
                }else{
                    if(!badSet.contains(j)){   // bad ----> good
                        TL[i][j] =  Rand.nextDouble(0.0,0.6);
                    }else{                     // bad ----> bad
                        TL[i][j] =  Rand.nextDouble(0.0,0.3);
                    }
                }
            }
        }
        Matrix matrix = new Matrix(TL);
        System.out.println("局部信任矩阵为：");
        matrix.print(2,3);
        return TL;
    }



    /**
     * 求解局部推荐度
     * @param TL 局部信任值
     * @param sita 安全因子
     * @return
     */
    public double[][] normalization(double[][] TL, double sita){
        double[][] TR = new double[TL.length][TL[0].length];
        for(int i=0; i<TL[0].length;i++){
            double sumRow = 0;
            for(int j=0;j<TL[0].length;j++){
                sumRow += TL[i][j];
            }
            for(int j=0;j<TL.length;j++){
                if(TL[i][j]>=sita) {
                    TR[i][j] = TL[i][j] / sumRow;
                }else{
                    TR[i][j] = 0;
                }
            }
        }
        Matrix matrix = new Matrix(TR);
        System.out.println("推荐矩阵为：");
        matrix.print(2,3);
        return TR;
    }

    /**
     * 信任计算
     * @param TR 推荐度
     * @param T 全局信任
     * @return
     */
    public double[][] trustCal(double[][] TR, double[][] T){

        Matrix mTR = new Matrix(TR);
        Matrix mT = new Matrix(T);
        Matrix matrix = mTR.times(mT);
        System.out.print("迭代信任值为： ");
        matrix.transpose().print(2,3);
        return matrix.getArray();
    }

    public void print(double[][] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                System.out.print(array[i][j]+"    ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int peerNum =10;
        MainTrust1 mainTrust1 = new MainTrust1();
        Set<Integer> hashSet = mainTrust1.initBadSet(peerNum,2);
        double[][] T = mainTrust1.initT(peerNum);
        double[][] TL = mainTrust1.initTL(peerNum, hashSet );
        double[][] TR = mainTrust1.normalization(TL,0.3);
        mainTrust1.trustCal(TR,T);
        for(int i=0;i<100;i++){
            double[][] tempT = mainTrust1.trustCal(TR,T);
            for(Integer badNum : hashSet){
                for(int j=0;j<peerNum;j++) {
                    if(j!=badNum)
                        TL[badNum][j] = TL[badNum][j] - 0.001;
                }
            }
            TR = mainTrust1.normalization(TL,0.3);
            T = tempT;
        }
    }


}
