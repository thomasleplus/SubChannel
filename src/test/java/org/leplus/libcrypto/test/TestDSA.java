package org.leplus.libcrypto.test;

import org.leplus.libcrypto.*;

import java.math.BigInteger;
import java.util.Date;

public final class TestDSA
{
	public static DSAKeyPairGenerator generator;
	
	public static DSASignatureEngine engine;
	
	public static DSAKeyPair pair;
	
	public static void main(String[] args)
		throws Exception {
		PRNGenerator random = new JavaPRNGenerator();
		generator = new DSAKeyPairGenerator(random);
		engine = new DSASignatureEngine(random);
		Date start = new java.util.Date();
		pair = (DSAKeyPair)generator.generateKeyPair();
		Date stop = new java.util.Date();
		System.out.println("Generation Time : " + ((double)(stop.getTime() - start.getTime()) / 1000) + " secondes");
		testPair(1000);
		test("", 0);
		test("", 1);
		test("abc", 1);
		test("abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq", 1);
		test("a", 1000000);
		/* DSA Test Vectors */
	}
	
	public static boolean testPair(int certainty)
		throws Exception {
		System.out.print("   p = " + pair.getP().toString(16) + " is prime : ");
		if (pair.getP().isProbablePrime(certainty))
			System.out.println("OK");
		else
			System.out.println("NO");
		System.out.print("   q = " + pair.getQ().toString(16) + " is prime : ");
		if (pair.getQ().isProbablePrime(certainty))
			System.out.println("OK");
		else
			System.out.println("NO");
		System.out.print("   q|p-1 : ");
		if (pair.getP().subtract(BigInteger.ONE).mod(pair.getQ()).signum() == 0)
			System.out.println("OK");
		else
			System.out.println("NO");
		System.out.println("   g = " + pair.getG().toString(16));
		System.out.println("   x = " + pair.getX().toString(16));
		System.out.println("   y = " + pair.getY().toString(16));
		return true;
	}
	
	public static boolean test(String vector, int loop)
		throws Exception {
		for (int i = 0; i < loop; i++)
			engine.update(vector.getBytes());
		DSASignature signature = (DSASignature)engine.sign(pair.getPrivateKey());
		for (int i = 0; i < loop; i++)
			engine.update(vector.getBytes());
		boolean b = engine.verify(pair.getPublicKey(), signature);
		if (b)
			System.out.println("Vector \"" + vector + "\" (x" + loop + ") : OK");
		else
			System.out.println("Vector \"" + vector + "\" (x" + loop + ") : NO");
		SHA1DigestEngine hash = new SHA1DigestEngine();
		for (int i = 0; i < loop; i++)
			hash.update(vector.getBytes());
		System.out.println("   r = " + signature.getR().toString(16));
		System.out.println("   s = " + signature.getS().toString(16));
		return b;
	}
}
