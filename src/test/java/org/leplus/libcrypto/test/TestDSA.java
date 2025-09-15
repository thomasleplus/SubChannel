package org.leplus.libcrypto.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.leplus.libcrypto.*;

public final class TestDSA {
  private DSAKeyPairGenerator generator;

  private DSASignatureEngine engine;

  private DSAKeyPair pair;

  @Test
  public void test() throws Exception {
    final PRNGenerator random = new JavaPRNGenerator();
    generator = new DSAKeyPairGenerator(random);
    engine = new DSASignatureEngine(random);
    final Date start = new java.util.Date();
    pair = (DSAKeyPair) generator.generateKeyPair();
    final Date stop = new java.util.Date();
    System.out.println(
        "Generation Time : " + ((double) (stop.getTime() - start.getTime()) / 1000) + " secondes");
    boolean b = true;
    b = testPair(1000) ? b : false;
    b = test("", 0) ? b : false;
    b = test("", 1) ? b : false;
    b = test("abc", 1) ? b : false;
    b = test("abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq", 1) ? b : false;
    b = test("a", 1000000) ? b : false;
    /* DSA Test Vectors */
    assertTrue(b);
  }

  private boolean testPair(int certainty) throws Exception {
    System.out.print("   p = " + pair.getP().toString(16) + " is prime : ");
    if (pair.getP().isProbablePrime(certainty)) System.out.println("OK");
    else System.out.println("NO");
    System.out.print("   q = " + pair.getQ().toString(16) + " is prime : ");
    if (pair.getQ().isProbablePrime(certainty)) System.out.println("OK");
    else System.out.println("NO");
    System.out.print("   q|p-1 : ");
    if (pair.getP().subtract(BigInteger.ONE).mod(pair.getQ()).signum() == 0)
      System.out.println("OK");
    else System.out.println("NO");
    System.out.println("   g = " + pair.getG().toString(16));
    System.out.println("   x = " + pair.getX().toString(16));
    System.out.println("   y = " + pair.getY().toString(16));
    return true;
  }

  private boolean test(String vector, int loop) throws Exception {
    for (int i = 0; i < loop; i++) engine.update(vector.getBytes());
    DSASignature signature = (DSASignature) engine.sign(pair.getPrivateKey());
    for (int i = 0; i < loop; i++) engine.update(vector.getBytes());
    boolean b = engine.verify(pair.getPublicKey(), signature);
    if (b) System.out.println("Vector \"" + vector + "\" (x" + loop + ") : OK");
    else System.out.println("Vector \"" + vector + "\" (x" + loop + ") : NO");
    SHA1DigestEngine hash = new SHA1DigestEngine();
    for (int i = 0; i < loop; i++) hash.update(vector.getBytes());
    System.out.println("   r = " + signature.getR().toString(16));
    System.out.println("   s = " + signature.getS().toString(16));
    return b;
  }
}
