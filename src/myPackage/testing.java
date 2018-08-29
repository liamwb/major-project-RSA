package myPackage;

import java.applet.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
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
	
		System.out.println("N is " + Methods.findN(17, 19) + ", and L is " + Methods.findL(17, 19));
		
		System.out.println("The encryption key is " + Methods.findE(288, 323));
		
		System.out.println("The message \"15\" is encrypted to " + Methods.encrypt(15, 5, 323));
		
		System.out.println("The encrypted message \"2\" is decrypted to " + Methods.decrypt(BigInteger.valueOf(2), Methods.findD(5, 288), BigInteger.valueOf(323)));


	}
}
