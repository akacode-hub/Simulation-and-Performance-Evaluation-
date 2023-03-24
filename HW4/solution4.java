package homework4;

import java.text.DecimalFormat;

import common.Rng;

class Outliers 
{
	long lo;
	long hi;
}

public class solution4 
{
	final static int NUM = 500;
	static double MIN = 0.0;
	static double MAX = 2.0;
	static int K = 20; /* number of histogram bins */
	static double DELTA = ((MAX - MIN) / K); /* histogram bin size (width) */

	public static void main(String[] args)
     {
		double x; /* data value */
		int j; /* histogram bin index */
		long index = 0; /* sample size */
		long count[] = new long[K]; /* bin count */
		double midpoint[] = new double[K]; /* bin midpoint */
		double sum = 0.0;
		double sumsqr = 0.0;
		double mean;
		double stdev;
		Outliers o = new Outliers();
		o.lo = 0;
		o.hi = 0;
		Rng r = new Rng();
		r.putSeed(-1);
		for (j = 0; j < K; j++) 
        {
			count[j] = 0;
			midpoint[j] = MIN + (j + 0.5) * DELTA;
		}
		while(index<NUM){
			index++;
			x = r.random() + r.random();
			if ((x >= MIN) && (x <= MAX)) 
            {
				j = (int) ((x - MIN) / DELTA);
				count[j]++;
			} else if (x < MIN)
				o.lo++;
			else
				o.hi++;
		}
		if (index > 0) 
        {
			for (j = 0; j < K; j++) /* histogram mean */
				sum += midpoint[j] * count[j];
			mean = sum / index;

			for (j = 0; j < K; j++) /* histogram stdev */
				sumsqr += Math.pow((midpoint[j] - mean), 2) * count[j];
			stdev = Math.sqrt(sumsqr / index);

			DecimalFormat f = new DecimalFormat("###0.000");

			System.out.println("bin\tmidpoint\tcount\tproportion\tdensity\n");
			for (j = 0; j < K; j++) 
            {
				System.out.print((j + 1) + "\t"); /* bin */
				System.out.print(f.format(midpoint[j]) + "\t\t"); /* midpoint */
				System.out.print(count[j] + "\t"); /* count */
				System.out.print(f.format((double) count[j] / index) + "\t\t"); /* proportion */
				System.out.println(f.format((double) count[j] / (index * DELTA))); /* density */
			}
			System.out.println("\nsample size .... = " + index);
			System.out.println("mean ........... = " + f.format(mean));
			System.out.println("stdev .......... = " + f.format(stdev) + "\n");
			if (o.lo > 0)
				System.out.println("NOTE: there were " + o.lo + " low outliers");
			if (o.hi > 0)
				System.out.println("NOTE: there were " + o.hi + " high outliers");
		}
	}
}