package myPackage;

import java.math.BigInteger;
import java.lang.reflect.Array;
import java.math.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Methods {
	
	//RSA methods
	
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

		for (BigInteger i = BigInteger.valueOf(2); i.subtract(BigInteger.ONE).compareTo(number) == -1; i = i.add(BigInteger.ONE)) {
			//this is the same as for (i = 2; i + 1 < number; i++), but using BigInteger because these numbers may get very large
				if (number.remainder(i).equals(BigInteger.valueOf(0))) {
					factors.add(i);
					i.add(BigInteger.ONE);
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
		boolean found = false; BigInteger T = BigInteger.valueOf(2); //since the while loop starts with T++, we will not test 1 as a factor
		BigInteger[] Tfactors; boolean testFailed = false;
/*The rule for E is that E is coprime with L & N (i.e. E, L, and N share no common factors (except 1)).
 * to find a suitable E, this method will, starting at 1, take some test number, T, and find its factors. Then,
 * for each factor of T, it will check if T % N or L ever returns 0, and if it does, then that T is not a suitable
 * E, and so T will be incremented by 1. If none of the factors of a trial number, T, go evenly into N or L, then that 
 * T is a suitable E, and so the method will return that T.
 * 
 * Additionally, 1 < E < L
 */
		while (!found) {
			if (T.equals(L)) return BigInteger.valueOf(-1); //T must be less than L
			Tfactors = findFactors(T);
			//System.out.println(Arrays.toString(Tfactors));
			
			for (int i = 0; i < Tfactors.length; i ++) {
				
				if (N.remainder(Tfactors[i]).equals(BigInteger.ZERO)) {
					//this is if (N % i == 0)
					//System.out.println(N + " % " + Tfactors[i] + " equals zero");
					testFailed = true;
					break;
				}
			}
			if (!testFailed) {
				//if there are no common factors with N
				for (int i = 0; i < Tfactors.length; i ++) {
					//the same for loop as before; to iterate through the array of factors
					if (L.remainder(Tfactors[i]).equals(BigInteger.ZERO)) {
						//System.out.println(L + " % " + Tfactors[i] + " equals zero");
						testFailed = true;
						break;
					}			
				}
			}
			
			if (!testFailed) {
				//System.out.println("test passed for " + T);
				break;
			}
			T = T.add(BigInteger.ONE); 
			//System.out.println("T incremented to " +T);
			testFailed = false;
			
			
		}
		return T;
	}
	
	public static BigInteger findD(BigInteger E, BigInteger L) {
/* The rule for finding D is D * E % L = 1
 * this method brute forces a solution for D, starting from 2
 */
		BigInteger i = BigInteger.valueOf(1);
		
		while (true) {
			i = i.add(BigInteger.ONE);
			if (i.multiply(E)  .remainder(L)   .equals(BigInteger.ONE)) {
				//ie if (i * E % L == 1) (this is the rule for D)
				break;
			}
		}
		return i;
	}

	
	//Overloaded RSA methods that take integer inputs
	
	public static BigInteger encrypt(int message, int E, int N) {
		return encrypt(BigInteger.valueOf(message), BigInteger.valueOf(E), BigInteger.valueOf(N));
	}
	
	public static BigInteger decrypt(int emessage, int D, int N) {
		return encrypt(BigInteger.valueOf(emessage), BigInteger.valueOf(D), BigInteger.valueOf(N));
	}
	
	public static BigInteger findN(int p, int q) {
		return BigInteger.valueOf(p)  .multiply( BigInteger.valueOf(q) );
	}
	
	public static BigInteger findL (int p, int q) {
		return BigInteger.valueOf(p-1)  .multiply( BigInteger.valueOf(q-1) );
	}
	
	public static BigInteger[] findFactors (int number) {
		return findFactors(BigInteger.valueOf(number));
	}
	
	public static BigInteger findE (int L, int N) {
		return findE (BigInteger.valueOf(L), BigInteger.valueOf(N));
	}
	
	public static BigInteger findD(int E, int L ) {
		return findD(BigInteger.valueOf(E), BigInteger.valueOf(L));
	}
}

