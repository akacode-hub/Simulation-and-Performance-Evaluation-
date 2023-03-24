package homework4;

import java.text.DecimalFormat;
import java.util.Random;

import javax.lang.model.util.ElementScanner14;

import common.Rng;

public class solution3 {
	final static int NUM = 100000;

	public static void main(String[] args) {
		Rng r = new Rng();
		int[] hist = new int[49];
		for (int i = 0; i < NUM; i++) {
			int score = 0;
			for (int j = 0; j < 12; j++) 
			{
				double rand = r.random();
				if (rand <= 0.25) {
					score += getGradeforClassII(r.random());
				} else {
					score += getGradeforClassI(r.random());
				}
			}
			hist[score]++;
		}
		int total = 0;
		DecimalFormat f = new DecimalFormat("###0.00000000");

		for (int i = 0; i < 49; i++) 
		{
			System.out.println("Score " + i +" " + f.format( (double) hist[i] / NUM));
			if (i >= 36)
				total += hist[i];
		}
		System.out.println("The probability of passing exam is " + (double) total / NUM);

	}

	private static int getGradeforClassI(double rand) 
	{
		if (rand <= 0.6)
			return 4;
		else if (rand >0.6 && rand <=0.9)
			return 3;
		else 
			return 2;
	}

	private static int getGradeforClassII(double rand) 
	{
		if (rand <= 0.1)
			return 3;
		else if (rand > 0.1 && rand <= 0.5)
			return 2;
		else if (rand > 0.5 && rand <= 0.9)
			return 1;
		else
			return 0;
	}
}