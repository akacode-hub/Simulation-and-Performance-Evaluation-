package homework3;

import java.awt.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.DoubleToLongFunction;
import common.*;
public class solution4 {
	static long LAST = 10000; /* number of jobs that needs to be processed */
	static double START = 0.0; /* initial time */
	static double sarrival = START; 

	public static void main(String[] args) {
			long index = 0; /* job index */
			double arrival = START; /* time of arrival */
			double delay = 0; /* delay in queue */
			double service; /* service time */
			double wait; /* delay + service */
			int capacity = 6; /* change the capacity 1,2,3,4,5,6 */
			double departure[] = new double[capacity + 1];
			Ssq2Sum sum = new Ssq2Sum();
			sum.initSumParas();
			int jobinqueue = 0;
			int reject = 0;
			Rng r = new Rng();
			r.putSeed(-1);
			while (index < LAST) {
				index++;				
				arrival = getArrival(r);
				if (arrival < departure[jobinqueue]) {
					if (jobinqueue < capacity) {
						delay = departure[jobinqueue]
								- arrival; /* delay in queue */
						service = getService(r);
						wait = delay + service;
						jobinqueue++;
						departure[jobinqueue] = arrival + wait;
					} else {
						if (arrival < departure[0]) {
							reject++;
							continue;
						} else {
							for (int i = 0; i < departure.length - 1; i++) {
								if (arrival > departure[i] && arrival < departure[i + 1]) {
									int k = 0;
									for (int j = i + 1; j <= departure.length - 1; j++) {
										departure[k] = departure[j];
										k++;
									}
									
									jobinqueue -= i;
									
									break;
								}
							}
							delay = departure[jobinqueue - 1] - arrival;
							service = getService(r);
							wait = delay + service;
							departure[jobinqueue] = arrival + wait;
						}
					}
				} else {
					jobinqueue = 0;
					delay = 0.0; 
					service = getService(r);
					wait = delay + service;
					departure[jobinqueue] = arrival + wait;
				}
			}
			DecimalFormat f = new DecimalFormat("###0.00000");

			System.out.println("\nfor " + index + " jobs");
			System.out.println("   rejection ............. =   " + f.format((double) reject / index));
	}

	static double exponential(double m, Rng r) {
		/*
		 * --------------------------------------------------- generate an
		 * Exponential random variate, use m > 0.0
		 * ---------------------------------------------------
		 */
		return (-m * Math.log(1.0 - r.random()));
	}

	static double uniform(double a, double b, Rng r) {
		/*
		 * ------------------------------------------------ generate an Uniform
		 * random variate, use a < b
		 * ------------------------------------------------
		 */
		return (a + (b - a) * r.random());
	}

	static double getArrival(Rng r) {
		/*
		 * ------------------------------ generate the next arrival time
		 * ------------------------------
		 */
		
		sarrival += exponential(2.0, r);
		return (sarrival);
	}

	static double getService(Rng r) {
		/*
		 * ------------------------------ generate the next service time
		 * ------------------------------
		 */
		return (uniform(1.0, 3.0, r));
	}
}