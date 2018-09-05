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
	
		
		System.out.println(Methods.encode("Oh my god it works!"));
		
		
		System.out.println(Methods.decode(Methods.encode("Oh my god it works!")));
		
		/*
		System.out.println("N is " + Methods.findN(15485867, 32452843) + ", and L is " + Methods.findL(15485867, 32452843));
		
		System.out.println("The encryption key is " + Methods.findE(BigInteger.valueOf((long) 502560362531172.0), BigInteger.valueOf((long)502560410469881.0)));
		
		System.out.println("The message \"5000\" is encrypted to " + Methods.encrypt(BigInteger.valueOf(5000), BigInteger.valueOf(5), BigInteger.valueOf((long)502560410469881.0)));
		
		System.out.println("The encrypted message \"5000\" is decrypted to " + Methods.decrypt(BigInteger.valueOf((long) 79367698279942.0), Methods.findD(BigInteger.valueOf(5), BigInteger.valueOf((long) 502560362531172.0)), BigInteger.valueOf(323)));
8*/

	}
}
