package math;

public class VectorCalculus {

	public static int argMax(double[] v) {
        if (v==null)
        	throw new NullPointerException("L'input è null.");
        else if (v.length==0) 
        	throw new IllegalArgumentException("L'input è un array di lunghezza 0.");
        int max = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[max] < v[i]) {
                max = i;
            }
        }
        return max;
	}

	public static double max(double[] v) {
		return v[argMax(v)];
	}
	
	public static double sampleMean(double[] v) {
        if (v==null)
        	throw new NullPointerException("L'input è null.");
        else if (v.length==0) 
        	throw new IllegalArgumentException("L'input è un array di lunghezza 0.");
        double mean = 0;
        for (int i = 0; i < v.length; i++) {
        	mean = mean + v[i];
        }
        return mean/v.length;
	}
	
}
