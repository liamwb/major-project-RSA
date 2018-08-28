package myPackage;

import java.math.BigInteger;
import java.math.*;
import java.util.ArrayList;
import java.util.List;

public class Methods {
	
	public static BigInteger encrypt(BigInteger message, BigInteger E, BigInteger N) {
		return message.modPow(E, N);
		//This is message ^ E % N
	}
	
	public static BigInteger decrypt(BigInteger emessage, BigInteger D, BigInteger N) {
		return emessage.modPow(D, N);
		//This is emessage ^ D % N
	}
	
	public static BigInteger findN(BigInteger p, BigInteger q) {
		return p.multiply(q);
	}
	
	public static BigInteger findL (BigInteger p, BigInteger q) {
		return (p.subtract(BigInteger.ONE)).multiply(q.subtract((BigInteger.ONE)));
		//this is (p-1)(q-1)
	}
	
	public static BigInteger[] findFactors (BigInteger number) {
		List<BigInteger> factors = new ArrayList<BigInteger>(); //A list so that factors can be easily appended as they are confirmed
		
		for (BigInteger i = BigInteger.TWO; i.compareTo(number.add(BigInteger.ONE)) == -1; i.add(BigInteger.ONE)) {
			System.out.println("i is " +i);
		//this is the same as for (i = 1; i + 1 < number; i++), but using BigInteger because these numbers may get very large
			if (i.mod(number).equals(BigInteger.ZERO)) {
			//if i % number == 0
				factors.add(i);
				System.out.println(1 + "is a factor");
			}
		}
		//putting the factors into an array. Arrays are faster than lists.
		BigInteger[] array = new BigInteger[factors.size()];
		for (int i = 0; i < factors.size(); i++) {
			array[i] = factors.get(i);
		}
		return array;
	}
	
	public static BigInteger findE(BigInteger L, BigInteger N) {
		boolean found = false; BigInteger T = BigInteger.ONE; //since the while loop starts with T++, we will not test 1 as a factor
		
/*The rule for E is that E is coprime with L & N (i.e. E, L, and N share no common factors (except 1)).
 * to find a suitable E, this method will, starting at 1, take some test number, T, and find its factors. Then,
 * for each factor of T, it will check if T % N or L ever returns 0, and if it does, then that T is not a suitable
 * E, and so T will be incremented by 1. If none of the factors of a trial number, T, go evenly into N or L, then that 
 * T is a suitable E, and so the method will return that T.
 * 
 * Additionally, 1 < E < L
 */
		while (!found) {
			if (T.equals(L)) return BigInteger.ONE; //T is less than L
			
			T.add(BigInteger.ONE); //T++
			BigInteger[] Tfactors = findFactors(T); boolean testFailed = false;
			int i = 0;
			while (!testFailed) {
				if (Tfactors[i].mod(L).equals(BigInteger.ZERO)) {
					i++; testFailed = true;
				}
				if (Tfactors[i].mod(N).equals(BigInteger.ZERO)) {
					i++; testFailed = true;
				}
			found = true;
			}
			
		}
		return T;
	}
	
	public static BigInteger findD(BigInteger E, BigInteger L) {
/* The rule for finding D is D * E % L = 1
 * this method brute forces a solution for D, starting from 2
 */
		boolean found = false; BigInteger i = BigInteger.ONE;
		while (!found) {
			i.add(BigInteger.ONE);
			if (E.multiply(i).mod(L).equals(BigInteger.ONE)) {
			//if (E * i % L == 1
				found = true;
			}
		}
		return i;
	}
}

