public class SequentialJacobi {

    public static double[] seqJac(double [][]A, double []b, double epsilon, int maxit){

        int n = A.length;
        double []x = new double[n];
        double []dx = new double[n];
        double []y = new double[n];
        int i, j, k, numit;	//temporary variables
		/* main loop */

        for(k = 0; k < maxit; k++)
        {
            double sum = 0.0;
            for(i = 0; i < n; i++)
            {
                dx[i] = b[i];
                for(j = 0; j < n; j++)
                    dx[i] -= A[i][j]*x[j];
                dx[i] /= A[i][i];
                y[i] += dx[i];
                sum += ( (dx[i] >= 0.0) ? dx[i] : -dx[i]);
            }
            for(i = 0; i < n; i++) x[i] = y[i];
            System.out.printf("%3d : %.3e\n", k, sum);
            if(sum <= epsilon) break;
        }
		/* main loop */
        numit = k+1;
        System.out.println("Number of iterations: " + numit);
        return x;
    }



    public static void main(String[] args){

        //fill in a random 10x10 sample problem
  /*      double c[][] = new double[10][10];
        double d[] = new double[10];
        for(int i = 0; i < c.length; i++)
            for(int j = 0; j< c.length; j++){
                c[i][j] = Math.random()*10;
                if(i ==j)
                    c[i][j] *= 100;
            }
        for(int i = 0; i < c.length; i++)
            d[i] = Math.random()+1;*/
        //
        double[][] cc = {
                {0.2,   0.1,   0.2,   0.3,   0.2,    0.3,  0.1},
                {0.1,   0.0,   0.0,   0.0,   0.02,  0.02,  0.02},
                {0.01,  0.0,   0.0,   0.0,   0.02,  0.02,  0.02},
                {0.01,  0.0,   0.0,   0.0,   0.02,  0.02,  0.02},
                {0.01,  0.0,   0.0,   0.0,   0.0,   0.02,  0.02},
                {0.01,  0.0,   0.04,  0.0,   0.02,  0.0,   0.02},
                {0.01,  0.01,  0.0,   0.0,   0.02,  0.02,  0.0}};
        double[][] c = {{10.0,-1.0,-2.0},{-1.0,10.0,-2.0},{-1.0,-1.0,5.0}};
        double[] d = {7.2,8.3,4.2};
        System.out.println("Matrices filled... ");
        System.out.println("Now solving sequantially... ");

        long start_time = System.nanoTime();
        double[] xVector = seqJac(c,d, 0.0001, 15);

        System.out.println("解集为");
        for(int i=0;i<xVector.length;i++){
            System.out.print(" "+xVector[i]);
        }
        long stop_time = System.nanoTime();
        long time_diff_millis = (stop_time - start_time)/(long)1.E6;
        System.out.println("Done.");
        System.out.println("Time in ms: " + time_diff_millis);

    }
}