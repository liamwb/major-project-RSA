package myPackage;

import java.applet.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class testing extends Applet {
	
	public static int[] findFactors (int number) {
		
		List<Integer> factors = new ArrayList<Integer>(); //A list so that factors can be easily appended as they are confirmed
		
		for (int i = 2; i + 1 < number; i++) {
		//this is the same as for (i = 1; i + 1 < number; i++), but using BigInteger because these numbers may get very large
			if (number % i == 0) {
			//if i % number == 0
				factors.add(i);
			}
		}
		//putting the factors into an array. Arrays are faster than lists.
		int[] array = new int[factors.size()];
		for (int i = 0; i < factors.size(); i++) {
			array[i] = factors.get(i);
		}
		return array;
	}
	
	
	public void init() {
		System.out.print(BigInteger.valueOf(6).remainder(BigInteger.valueOf(3)));
		//System.out.println(Methods.findFactors(BigInteger.valueOf(6)));
		
		for (int i = 0; i < Methods.findFactors(BigInteger.valueOf(6)).length; i++) {
			System.out.println(Methods.findFactors(BigInteger.valueOf(6))[i]);
		}
		
		/*for (BigInteger i = BigInteger.valueOf(2); i.add(BigInteger.ONE).compareTo(BigInteger.valueOf(6)) == -1; i.add(BigInteger.ONE)) {
			System.out.println(i);
			System.out.println(i.add(BigInteger.ONE).compareTo(BigInteger.valueOf(6)) == -1);
			i.add(BigInteger.ONE);
		}*/
		
		
		//for (BigInteger i = BigInteger.valueOf(2); i.subtract(BigInteger.ONE).compareTo(BigInteger.valueOf(6)) == -1; i = i.add(BigInteger.ONE)) {

	}
}
