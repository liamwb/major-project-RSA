package myPackage;

import java.math.BigInteger;
import java.nio.charset.Charset;
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
					//if the number is a factor
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
	/*A good explanation of this method can be found here:
	 * https://stackoverflow.com/questions/23279208/rsa-calculate-d
	 * 
	 * The decryption key, D, must follow the rule d*e = 1 mod(L)
	 * or (d*e) mod L = 1. (I assume these statements are mathematically equivalent)
	 * 
	 * So from d*e = 1 modL
	 * d*e = 1 + k*L, k is a natural number
	 * 
	 * So d = (1 + k*L)/e, and d must be an integer.
	 * 
	 * So this method will loop through k until it finds an integer solution for d
	 */
		BigInteger k = BigInteger.ONE;
		while (true) {
			
			if (    (BigInteger.ONE.add(   k.multiply(L)    )    ).      remainder(E).     equals(BigInteger.ZERO)) {
			//if ((1 + k*L) / E == 0
			//ie if the above is an integer
				return (BigInteger.ONE.add(   k.multiply(L)    )    ).divide(E);
				//return (1 + k*L) / E
			}
			
			
			k = k.add(BigInteger.ONE);
			
			
		}
		
	}
	
	/*old findD method that just brute forced out a solution from every single natural number.
	public static BigInteger findD(BigInteger E, BigInteger L) {
 * The rule for finding D is D * E % L = 1
 * this method brute forces a solution for D, starting from 2
 *
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
*/
	
	//method to convert strings into numbers and numbers into strings (based on char values)

	public static BigInteger encode (String s) {
		byte[] byteArray = s.getBytes(Charset.forName("ASCII"));
		String concatable = "1";
//the string starts with a 1 so that java doesn't cut off any of the leading zeros that get put there automatically
		
		for (int i = 0; i < byteArray.length; i++) {
			String candidate =  "" + byteArray[i];
			//need to enforce that each char ends up at three numerals, so that it can be decoded
			switch (candidate.length()) 
			{
			case 1: candidate = "00" + candidate;
				
			case 2: candidate = "0" + candidate;
			}
			concatable += candidate;	
		}
		
		BigInteger output = new BigInteger(concatable);
		return output;
	}
	
	public static String decode(BigInteger number) {
		String numberString = number.toString();
		numberString = numberString.substring(1);
		String output = "";
		//This substring will remove the '1' that was added to the start of number by the encode method
		for (int i = 0; i < numberString.length(); i += 3) {
	//each three digits translate to a single ASCII character
			output += (char) Integer.parseInt(     numberString.substring(i, i+3)      );
/*There are a few things going on here:
 * numberString.substring(i, i+3) is going to return a chunk of three numerals     
 * Those numerals are going to be converted to an integer
 * And that integer is going to be cast to a char, which will automatically convert the integer representation
 * to a char, which can then be concatenated to the output
 */
		}
		return output;
	}
}

