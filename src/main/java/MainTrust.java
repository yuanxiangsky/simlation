import Jama.Matrix;

/**
 * Created by YuanXiang on 2017/12/1.
 */
public class MainTrust {
    public static void main(String[] args) {

        double[][] c = {
                {1.0,   -0.03,  -0.02, -0.01,  0.0,  0.0,  0.0},
                {-0.1,  1.0,  0.0,  0.0,  -0.02, -0.02, -0.02},
                {-0.01,  0.0,  1.0,  0.0,  -0.02, -0.02, -0.02},
                {-0.01,  0.0,  0.0,  1.0,  -0.02, -0.02, -0.02},
                {-0.01,  0.0,  0.0,  0.0,   1.0, -0.02, -0.02},
                {-0.01,  0.0,  -0.04,  0.0,  -0.02,  1.0, -0.02},
                {-0.01,  -0.01,  0.0,  0.0,  -0.02, -0.02,  1.0}};
        double[][] d ={{0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5}};
        long start_time = System.nanoTime();
/*    //    double[] xVector = SequentialJacobi.seqJac(c,d, 0.0001, 50);

        System.out.println("解集为");
        for(int i=0;i<xVector.length;i++){
            System.out.print("----"+xVector[i]);
        }
        System.out.println();
        long stop_time = System.nanoTime();
        long time_diff_millis = (stop_time - start_time)/(long)1.E6;
        System.out.println("Done.");
        System.out.println("Time in ms: " + time_diff_millis);*/

        Matrix matrix1 = new Matrix(c);
        Matrix matrix2 = new Matrix(d);
        matrix1.print(1,2);
        matrix2.transpose().print(1,2);



    }



}
