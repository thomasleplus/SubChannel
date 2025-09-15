package org.leplus.libcrypto;

import java.math.BigInteger;
import java.security.InvalidParameterException;

/**
 * Générateur de Pair de Clés Cryptographiques DSA.
 *
 * <p>Le <i>Digital Signature Algorithm</i> est défini par la norme <a
 * href="https://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS186-2</a>
 * (<i>Digital Signature Scheme</i>) du NIST.
 */
public final class DSAKeyPairGenerator extends KeyPairGenerator {

  /** 2 en BigInteger. */
  private static final BigInteger two = BigInteger.valueOf(2);

  /**
   * Pour un Q choisit aléatoirement, LIMIT est le nombre de tentatives pour trouver P tel que Q
   * divise P-1 avant de changer de Q.
   */
  private static final int LIMIT = 4096;

  /** La longueur des clés générées (en octets). */
  private final int length;

  /** La certitude du teste de primalité . */
  private final int certainty;

  /** Le générateur de nombres aléatoires. */
  private final PRNGenerator random;

  /**
   * Construit un générateur de pairs de clés DSA de 1024 bits (avec une certitude de primalité à
   * 100).
   *
   * @param prng le générateur de nombres pseudo-aléatoires à utiliser.
   */
  public DSAKeyPairGenerator(final PRNGenerator prng) throws InvalidParameterException {
    random = prng;
    length = 128;
    certainty = 100;
  }

  /**
   * Construit un générateur de pairs de clés DSA.
   *
   * @param prng le générateur de nombres pseudo-aléatoires à utiliser.
   * @param t le paramêtre de sécurité T compris entre 0 et 8 (la taille des clés sera 512+64T
   *     bits). <b>T > 3 est fortement recommendé depuis 1996.</b>
   * @param c la certitude voulu sur la primalité des entiers générés: les entiers sont premiers
   *     avec une probabilité p = (1-1/2<sup>c</sup>).
   * @throws InvalidParameterException si t est inférieur à 0 ou supérieur à 8.
   */
  public DSAKeyPairGenerator(final PRNGenerator prng, final int t, final int c)
      throws InvalidParameterException {
    if (t < 0 || t > 8) {
      throw new InvalidParameterException("T < 0 || T > 8");
    }
    random = prng;
    length = 64 + 8 * t;
    certainty = c;
  }

  /**
   * Génère une nouvelle pair de clés.
   *
   * @return la pair de clés.
   */
  @Override
  protected KeyPair doGenerateKeyPair() {
    BigInteger p;
    BigInteger q;
    int counter;
    do {
      p = two;
      q = random.getBigPrime(160, 160, certainty);
      for (counter = 0; counter < LIMIT; counter++) {
        final BigInteger t = random.getBigInt(length << 3, length << 3);
        p = t.subtract(t.mod(two.multiply(q))).add(BigInteger.ONE);
        if (p.bitLength() == length << 3 && p.isProbablePrime(certainty)) {
          break;
        }
      }
    } while (counter == LIMIT);
    BigInteger g;
    do {
      g =
          random
              .getBigInt(two, p.subtract(two))
              .modPow(
                  p.subtract(BigInteger.ONE).divide(q), p); /* @TL FIXME: Why is g a generator ? */
    } while (g.compareTo(BigInteger.ONE) == 0);
    final BigInteger x = random.getBigInt(BigInteger.ONE, q.subtract(BigInteger.ONE));
    final BigInteger y = g.modPow(x, p);
    final DSAPrivateKey pvk = new DSAPrivateKey(p, q, g, x);
    final DSAPublicKey pbk = new DSAPublicKey(p, q, g, y);
    return new DSAKeyPair(pvk, pbk);
  }

  /**
   * Retourne la longueur en octets des clés générées (en octets).
   *
   * @return la longueur des clés (en octets).
   */
  @Override
  protected int doGetLength() {
    return length;
  }
}
