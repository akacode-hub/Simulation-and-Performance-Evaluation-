package hw2;

import java.text.DecimalFormat;

import common.Rng;
import common.*;
import common.*;
public class solution3 
{
	public static void main(String[] args) 
	{
		int N = 10000;
		int n = 0;
		Rng r = new Rng();
		r.putSeed(-1);
		for (int i = 0; i < N; i++)
		 {
			int face1 = unfairdice(r);
			int face2 = unfairdice(r);
			if (face1 + face2 == 7)
			{
				n++;
			}
		}
		DecimalFormat f = new DecimalFormat("###0.00000");
		System.out.println("probability is " + f.format((double)n/N));
	}
    // Logic to calculate the random cases of the unfair dice 
	public static int equilikely(long a, long b, Rng r) 
	{
		return (int) (a + (long) ((b - a + 1) * r.random()));
	}
    // Deciding the cases of how to solve unfair dice 
	public static int unfairdice(Rng r) 
	{
		int num = equilikely(1, 13, r);
		int face = 0;
		switch (num)
		 {
		case 1:
			face = 1;
			break;
		case 2:
		case 3:
			face = 2;
			break;
		case 4:
		case 5:
			face = 3;
			break;
		case 6:
		case 7:
			face = 4;
			break;
		case 8:
		case 9:
			face = 5;
			break;
		case 10:
		case 11:
		case 12:
		case 13:
			face = 6;
			break;
		}
		return face;
	}
}