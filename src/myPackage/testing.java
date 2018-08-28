package myPackage;

import java.applet.*;
import java.math.BigInteger;

public class testing extends Applet {
	public void init() {
		System.out.println(Methods.findN(BigInteger.valueOf(2), BigInteger.valueOf(7)));
		System.out.println(Methods.findL(BigInteger.valueOf(2), BigInteger.valueOf(7)));
		System.out.println(Methods.findE(BigInteger.valueOf(2), BigInteger.valueOf(7)));
	}
}
