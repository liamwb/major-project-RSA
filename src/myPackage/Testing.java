package myPackage;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

public class Testing {
	public static BigInteger p; public static BigInteger q;
	
	static int tried; static int correct;
	

	public static void main(String[] args) {
		p = (BigInteger.TWO.pow(4423)).subtract(BigInteger.ONE);
		
		q = (BigInteger.TWO.pow(9689)).subtract(BigInteger.ONE);
		
		tried = 0; correct = 0;
		BigInteger N = Methods.findN(p, q);
		BigInteger L = Methods.findL(p, q);
		BigInteger E = Methods.findE(L, N);
		BigInteger D = Methods.findD(E, L);
		
	
		for (int i = 1; i < 1001; i++) {
			tried += 1;
			String test = UUID.randomUUID().toString();;
			
			
			
			System.out.println("Test is " + test);
			
			BigInteger encrypted = Methods.encrypt(Methods.encode(test), E, N);
			String result = Methods.decode(Methods.decrypt(encrypted, D, N));
			
			if (test.equals(result)) {
				correct ++;
			}
			
		}
		
		System.out.println("Tried: " + tried + "\nCorrect: " + correct);
	}
	
	
}
